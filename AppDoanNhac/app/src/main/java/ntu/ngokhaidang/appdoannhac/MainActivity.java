package ntu.ngokhaidang.appdoannhac;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView CDImg;
    Button nutBatDau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        CDImg = findViewById(R.id.imgCD);
        nutBatDau = findViewById(R.id.btnBatDau);

        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(CDImg, "rotation", 0f, 360f);
        rotateAnimator.setDuration(10000); // Thời gian xoay 1 vòng (10 giây)
        rotateAnimator.setInterpolator(new LinearInterpolator()); // Xoay đều
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // Lặp vô hạn
        rotateAnimator.start();

        nutBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Choi = new Intent(MainActivity.this, Quiz.class);
                startActivity(Choi);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}