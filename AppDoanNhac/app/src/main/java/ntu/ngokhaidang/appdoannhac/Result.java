package ntu.ngokhaidang.appdoannhac;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {
    TextView tbKetQua;
    ImageButton nutTroVe;
    int sodiem;
    int tongSoCau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        tbKetQua = findViewById(R.id.ketqua);
        nutTroVe = findViewById(R.id.btnQuit);

        // Lấy dữ liệu điểm và tổng số câu từ Intent
        sodiem = getIntent().getIntExtra("score", 0);
        tongSoCau = getIntent().getIntExtra("total", 0);

        // Hiển thị số câu đúng (score / 100 vì mỗi câu đúng +100 điểm)
        int cauTLDung = sodiem / 100;
        tbKetQua.setText("Bạn trả lời đúng " + cauTLDung + " / " + tongSoCau + " câu!\nĐiểm của bạn: " + sodiem);

        nutTroVe.setOnClickListener(v -> {
            finish(); // Quay lại màn hình chính
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}