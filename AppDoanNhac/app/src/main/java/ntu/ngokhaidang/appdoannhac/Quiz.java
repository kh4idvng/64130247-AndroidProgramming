package ntu.ngokhaidang.appdoannhac;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {

    // Khai báo biến giao diện
    Button nutNhac, dapAn1, dapAn2, dapAn3, dapAn4;
    ImageView animeImage;
    TextView soCau, timerText;

    // Biến điều khiển logic
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    DatabaseReference databaseReference;
    List<Question> questionList = new ArrayList<>();
    Question currentQuestion;

    // Biến trạng thái
    int currentQuestionIndex = 0;
    int score = 0;
    boolean isPlaying = false;
    boolean hasAnswered = false;

    // Thời gian cho mỗi câu hỏi (15 giây)
    final long totalTime = 15000;
    long timeLeftInMillis = totalTime;

    // tìm điều khiển
    private void TimDieuKhien() {
        animeImage = findViewById(R.id.animeImage);
        nutNhac = findViewById(R.id.nutChoiNhac);
        dapAn1 = findViewById(R.id.nutDA);
        dapAn2 = findViewById(R.id.nutDA1);
        dapAn3 = findViewById(R.id.nutDA2);
        dapAn4 = findViewById(R.id.nutDA3);
        timerText = findViewById(R.id.timerText);
        soCau = findViewById(R.id.soCau);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Tìm điều khiển
        TimDieuKhien();

        // Kết nối Firebase và lấy dữ liệu câu hỏi
        databaseReference = FirebaseDatabase.getInstance().getReference("questions");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question question = dataSnapshot.getValue(Question.class);
                    questionList.add(question);
                }

                // Hiển thị câu hỏi đầu tiên nếu danh sách không rỗng
                if (!questionList.isEmpty()) {
                    showQuestion(currentQuestionIndex);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Quiz.this, "Lỗi tải dữ liệu!", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Play/Pause nhạc
        nutNhac.setOnClickListener(v -> {
            if (isPlaying) {
                if (mediaPlayer != null) mediaPlayer.pause();
                nutNhac.setText("Play");
            } else {
                if (mediaPlayer != null) mediaPlayer.start();
                nutNhac.setText("Pause");
                startTimer();
            }
            isPlaying = !isPlaying;
        });
    }


    // Hiển thị câu hỏi tại chỉ số index
    private void showQuestion(int index) {
        if (index >= questionList.size()) {
            // Khi hết câu hỏi, chuyển sang màn hình kết quả
            Intent intent = new Intent(Quiz.this, Result.class);
            intent.putExtra("score", score);
            intent.putExtra("total", questionList.size());
            startActivity(intent);
            finish();
            return;
        }

        currentQuestion = questionList.get(index);
        hasAnswered = false;

        // Dừng nhạc và đếm giờ cũ nếu có
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        isPlaying = false;
        nutNhac.setText("Play");

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        timeLeftInMillis = totalTime;
        timerText.setText("15");

        // Hiển thị các đáp án
        dapAn1.setText(currentQuestion.getOption1());
        dapAn2.setText(currentQuestion.getOption2());
        dapAn3.setText(currentQuestion.getOption3());
        dapAn4.setText(currentQuestion.getOption4());

        // Reset giao diện nút và hiển thị số câu
        resetAnswerButtons();
        soCau.setText("Câu " + (index + 1));

        // Hiển thị hình mặc định khi chưa chọn đáp án
        animeImage.setVisibility(View.VISIBLE);
        animeImage.setImageResource(R.drawable.bd);

        // Chuẩn bị nhạc câu hỏi
        int musicResId = getResources().getIdentifier(currentQuestion.getMusicName(), "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, musicResId);

        // Gán sự kiện chọn đáp án
        setAnswerListener(dapAn1, 0);
        setAnswerListener(dapAn2, 1);
        setAnswerListener(dapAn3, 2);
        setAnswerListener(dapAn4, 3);
    }

    // Gán sự kiện khi người dùng chọn đáp án
    private void setAnswerListener(Button button, int selectedIndex) {
        button.setOnClickListener(v -> {
            if (hasAnswered) return;
            hasAnswered = true;

            countDownTimer.cancel();

            int correctIndex = currentQuestion.getCorrectAnswerIndex();

            if (selectedIndex == correctIndex) {
                button.setBackgroundColor(Color.GREEN);
                score += 100;
            } else {
                button.setBackgroundColor(Color.RED);
                highlightCorrectAnswer(correctIndex);
            }

            showAnimeImage();
            disableAllAnswerButtons();

            // Tự động chuyển sang câu kế tiếp sau 1 giây
            nutNhac.postDelayed(() -> {
                currentQuestionIndex++;
                showQuestion(currentQuestionIndex);
            }, 1000);
        });
    }

    // Tô màu xanh cho đáp án đúng
    private void highlightCorrectAnswer(int correctIndex) {
        Button[] buttons = {dapAn1, dapAn2, dapAn3, dapAn4};
        buttons[correctIndex].setBackgroundColor(Color.GREEN);
    }

    // Hiển thị hình ảnh của bộ hoạt hình đúng khi trả lời
    private void showAnimeImage() {
        int imageResId = getResources().getIdentifier(currentQuestion.getImageName(), "drawable", getPackageName());
        animeImage.setImageResource(imageResId);
        animeImage.setVisibility(View.VISIBLE);
    }

    // Reset màu sắc và trạng thái các nút đáp án
    private void resetAnswerButtons() {
        Button[] buttons = {dapAn1, dapAn2, dapAn3, dapAn4};
        for (Button b : buttons) {
            b.setBackgroundColor(Color.WHITE);
            b.setEnabled(true);
        }
    }

    // Bắt đầu đếm ngược thời gian cho mỗi câu hỏi
    private void startTimer() {
        timeLeftInMillis = totalTime;

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                timerText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                if (hasAnswered) return;
                hasAnswered = true;

                disableAllAnswerButtons();
                highlightCorrectAnswer(currentQuestion.getCorrectAnswerIndex());
                showAnimeImage();

                Toast.makeText(Quiz.this, "Hết giờ!", Toast.LENGTH_SHORT).show();

                // Tự chuyển câu sau 1 giây
                nutNhac.postDelayed(() -> {
                    currentQuestionIndex++;
                    showQuestion(currentQuestionIndex);
                }, 1000);
            }
        }.start();
    }

    // Vô hiệu hóa toàn bộ nút đáp án
    private void disableAllAnswerButtons() {
        dapAn1.setEnabled(false);
        dapAn2.setEnabled(false);
        dapAn3.setEnabled(false);
        dapAn4.setEnabled(false);
    }
    // Giải phóng tài nguyên khi thoát Activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
