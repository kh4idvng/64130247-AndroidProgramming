package ntu.ngokhaidang.appdoannhac;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView CDImg;
    ImageButton nutBatDau;

    MediaPlayer nhacnen;
    MediaPlayer amthanhnutbatdau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //tìm điều khiển
        CDImg = findViewById(R.id.imgCD);
        nutBatDau = findViewById(R.id.btnStart);

        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(CDImg, "rotation", 0f, 360f);
        rotateAnimator.setDuration(10000); // Thời gian xoay 1 vòng (10 giây)
        rotateAnimator.setInterpolator(new LinearInterpolator()); // Xoay đều
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // Lặp vô hạn
        rotateAnimator.start();

        // thêm âm thanh cho nút Start
        amthanhnutbatdau = MediaPlayer.create(this, R.raw.nutbatdau);

        // nhac nen
        nhacnen = MediaPlayer.create(this, R.raw.nhacnen);
        nhacnen.setLooping(true);
        nhacnen.start();

        // bắt sự kiện nút Start
        nutBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dừng và giải phóng nhạc nền khi chuyển sang Quiz
                if (nhacnen != null && nhacnen.isPlaying()) {
                    nhacnen.pause();
                }
                Intent Choi = new Intent(MainActivity.this, Quiz.class);
                startActivity(Choi);
                amthanhnutbatdau.start();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (nhacnen != null && !nhacnen.isPlaying()) {
            nhacnen.start();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (nhacnen != null) {
            nhacnen.release();
            nhacnen.stop();
            nhacnen = null;
        }
    }
}