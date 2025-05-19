package ntu.ngokhaidang.appdoannhac;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.WebView;
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
    ImageView anhHH;
    TextView soCau, timerText;

    MediaPlayer mediaPlayer;
    CountDownTimer demNguocTG;
    DatabaseReference databaseReference;
    List<Question> dsCauHoi = new ArrayList<>();
    Question currentQuestion;
    WebView gif_1, gif_2, gif_3, gif_4;
    int soCauHienTai = 0;
    int diemso = 0;
    boolean isPlaying = false;
    boolean hasAnswered = false;
    boolean batDauDemNguoc = false;

    final long tongTG = 15000;
    long tgConLai = tongTG;
    private void TimDieuKhien() {
        anhHH = findViewById(R.id.animeImage);
        nutNhac = findViewById(R.id.nutChoiNhac);
        dapAn1 = findViewById(R.id.nutDA);
        dapAn2 = findViewById(R.id.nutDA1);
        dapAn3 = findViewById(R.id.nutDA2);
        dapAn4 = findViewById(R.id.nutDA3);
        timerText = findViewById(R.id.timerText);
        soCau = findViewById(R.id.soCau);
        gif_2 = findViewById(R.id.gif2);
        gif_1 = findViewById(R.id.gif1);
        gif_3 = findViewById(R.id.gif3);
        gif_4 = findViewById(R.id.gif4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TimDieuKhien();
        gif_1.loadUrl("file:///android_res/drawable/liz.gif");
        gif_1.getSettings().setLoadWithOverviewMode(true);
        gif_1.getSettings().setUseWideViewPort(true);

        gif_2.loadUrl("file:///android_res/drawable/dancindog.gif");
        gif_2.getSettings().setLoadWithOverviewMode(true);
        gif_2.getSettings().setUseWideViewPort(true);

        gif_3.loadUrl("file:///android_res/drawable/dancingco.gif");
        gif_3.getSettings().setLoadWithOverviewMode(true);
        gif_3.getSettings().setUseWideViewPort(true);

        gif_4.loadUrl("file:///android_res/drawable/cat2.gif");
        gif_4.getSettings().setLoadWithOverviewMode(true);
        gif_4.getSettings().setUseWideViewPort(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("questions");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question question = dataSnapshot.getValue(Question.class);
                    dsCauHoi.add(question);
                }

                if (!dsCauHoi.isEmpty()) {
                    showQuestion(soCauHienTai);
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
                nutNhac.setText("Chơi");
            } else {
                if (mediaPlayer != null) mediaPlayer.start();
                // Chỉ bật khi nhạc được phát
                batNutDapAn();
                nutNhac.setText("Dừng");
                if (!batDauDemNguoc) {
                    startTimer();
                    batDauDemNguoc = true;
                }
            }
            isPlaying = !isPlaying;
        });
    }

    private void showQuestion(int index) {
        if (index >= dsCauHoi.size()) {
            Intent intent = new Intent(Quiz.this, Result.class);
            intent.putExtra("score", diemso);
            intent.putExtra("total", dsCauHoi.size());
            startActivity(intent);
            finish();
            return;
        }

        currentQuestion = dsCauHoi.get(index);
        hasAnswered = false;
        batDauDemNguoc = false;

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        isPlaying = false;
        nutNhac.setText("Chơi");

        if (demNguocTG != null) {
            demNguocTG.cancel();
        }

        tgConLai = tongTG;
        timerText.setText("15");

        dapAn1.setText(currentQuestion.getOption1());
        dapAn2.setText(currentQuestion.getOption2());
        dapAn3.setText(currentQuestion.getOption3());
        dapAn4.setText(currentQuestion.getOption4());

        resetAnswerButtons();
        // Vô hiệu hóa nút đáp án
        tatNutDapAn();
        soCau.setText("Câu " + (index + 1));

        anhHH.setVisibility(View.VISIBLE);
        anhHH.setImageResource(R.drawable.bd);

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

            demNguocTG.cancel();

            int correctIndex = currentQuestion.getCorrectAnswerIndex();

            if (selectedIndex == correctIndex) {
                button.setBackgroundColor(Color.GREEN);
                diemso += 100;
            } else {
                button.setBackgroundColor(Color.RED);
                highlightCorrectAnswer(correctIndex);
            }

            showAnimeImage();
            tatNutDapAn();

            nutNhac.postDelayed(() -> {
                soCauHienTai++;
                showQuestion(soCauHienTai);
            }, 2000);
        });
    }

    private void highlightCorrectAnswer(int correctIndex) {
        Button[] buttons = {dapAn1, dapAn2, dapAn3, dapAn4};
        buttons[correctIndex].setBackgroundColor(Color.GREEN);
    }

    private void showAnimeImage() {
        int imageResId = getResources().getIdentifier(currentQuestion.getImageName(), "drawable", getPackageName());
        anhHH.setImageResource(imageResId);
        anhHH.setVisibility(View.VISIBLE);
    }

    private void resetAnswerButtons() {
        Button[] buttons = {dapAn1, dapAn2, dapAn3, dapAn4};
        for (Button b : buttons) {
            b.setBackgroundColor(Color.WHITE);
            b.setEnabled(true);
        }
    }

    private void tatNutDapAn() {
        dapAn1.setEnabled(false);
        dapAn2.setEnabled(false);
        dapAn3.setEnabled(false);
        dapAn4.setEnabled(false);
    }

    private void batNutDapAn() {
        dapAn1.setEnabled(true);
        dapAn2.setEnabled(true);
        dapAn3.setEnabled(true);
        dapAn4.setEnabled(true);
    }

    private void startTimer() {
        tgConLai = tongTG;

        if (demNguocTG != null) {
            demNguocTG.cancel();
        }

        demNguocTG = new CountDownTimer(tongTG, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tgConLai = millisUntilFinished;
                timerText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                if (hasAnswered) return;
                hasAnswered = true;

                tatNutDapAn();
                highlightCorrectAnswer(currentQuestion.getCorrectAnswerIndex());
                showAnimeImage();

                Toast.makeText(Quiz.this, "Hết giờ!", Toast.LENGTH_SHORT).show();

                nutNhac.postDelayed(() -> {
                    soCauHienTai++;
                    showQuestion(soCauHienTai);
                }, 1000);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (demNguocTG != null) {
            demNguocTG.cancel();
        }
    }
}
