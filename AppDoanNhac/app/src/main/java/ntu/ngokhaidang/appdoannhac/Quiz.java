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
    Button nutNhac, dapAn1, dapAn2, dapAn3, dapAn4;
    ImageView animeImage;
    MediaPlayer mediaPlayer;
    boolean isPlaying = false;
    TextView soCau;
    DatabaseReference databaseReference;
    List<Question> questionList = new ArrayList<>();
    int currentQuestionIndex = 0;
    CountDownTimer countDownTimer;
    int score = 0;
    final long totalTime = 15000; // 15 giây
    long timeLeftInMillis = totalTime;

    Question currentQuestion;
    boolean hasAnswered = false;

    TextView timerText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        animeImage = findViewById(R.id.animeImage);
        nutNhac = findViewById(R.id.nutChoiNhac);
        dapAn1 = findViewById(R.id.nutDA);
        dapAn2 = findViewById(R.id.nutDA1);
        dapAn3 = findViewById(R.id.nutDA2);
        dapAn4 = findViewById(R.id.nutDA3);
        timerText = findViewById(R.id.timerText);
        soCau = findViewById(R.id.soCau);

        databaseReference = FirebaseDatabase.getInstance().getReference("questions");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question question = dataSnapshot.getValue(Question.class);
                    questionList.add(question);
                }
                if (!questionList.isEmpty()) {
                    showQuestion(currentQuestionIndex);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Quiz.this, "Lỗi tải dữ liệu!", Toast.LENGTH_SHORT).show();
            }
        });

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

    private void showQuestion(int index) {
        if (index >= questionList.size()) {
            Intent intent = new Intent(Quiz.this, Result.class);
            intent.putExtra("score", score);
            intent.putExtra("total", questionList.size());
            startActivity(intent);
            finish();
            return;
        }

        currentQuestion = questionList.get(index);
        hasAnswered = false;

        // reset nut play va thoi gian
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

        dapAn1.setText(currentQuestion.getOption1());
        dapAn2.setText(currentQuestion.getOption2());
        dapAn3.setText(currentQuestion.getOption3());
        dapAn4.setText(currentQuestion.getOption4());

        resetAnswerButtons();
        soCau.setText("Câu " + (index + 1));

        animeImage.setVisibility(View.VISIBLE);
        animeImage.setImageResource(R.drawable.bd);

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        int musicResId = getResources().getIdentifier(currentQuestion.getMusicName(), "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, musicResId);

        setAnswerListener(dapAn1, 0);
        setAnswerListener(dapAn2, 1);
        setAnswerListener(dapAn3, 2);
        setAnswerListener(dapAn4, 3);
    }

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

            nutNhac.postDelayed(() -> {
                currentQuestionIndex++;
                showQuestion(currentQuestionIndex);
            }, 2000);
        });
    }

    private void highlightCorrectAnswer(int correctIndex) {
        switch (correctIndex) {
            case 0:
                dapAn1.setBackgroundColor(Color.GREEN);
                break;
            case 1:
                dapAn2.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                dapAn3.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                dapAn4.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    private void showAnimeImage() {
        int imageResId = getResources().getIdentifier(currentQuestion.getImageName(), "drawable", getPackageName());
        animeImage.setImageResource(imageResId);
        animeImage.setVisibility(View.VISIBLE);
    }

    private void resetAnswerButtons() {
        Button[] buttons = {dapAn1, dapAn2, dapAn3, dapAn4};
        for (Button b : buttons) {
            b.setBackgroundColor(Color.parseColor("#ffffff"));
            b.setEnabled(true);
        }
    }

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

                nutNhac.postDelayed(() -> {
                    currentQuestionIndex++;
                    showQuestion(currentQuestionIndex);
                }, 2000);
            }
        }.start();
    }

    private void disableAllAnswerButtons() {
        dapAn1.setEnabled(false);
        dapAn2.setEnabled(false);
        dapAn3.setEnabled(false);
        dapAn4.setEnabled(false);
    }

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
