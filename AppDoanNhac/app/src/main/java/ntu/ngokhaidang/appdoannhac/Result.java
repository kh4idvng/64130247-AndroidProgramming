package ntu.ngokhaidang.appdoannhac;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {
    TextView resultText;
    Button backButton;
    int score;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.resultText);
        backButton = findViewById(R.id.backButton);

        // Lấy dữ liệu điểm và tổng số câu từ Intent
        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 0);

        // Hiển thị số câu đúng (score / 100 vì mỗi câu đúng +100 điểm)
        int correctAnswers = score / 100;
        resultText.setText("Bạn trả lời đúng " + correctAnswers + " / " + total + " câu!\nĐiểm của bạn: " + score);

        backButton.setOnClickListener(v -> {
            finish(); // Quay lại màn hình trước
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}