package ntu.ngokhaidang.checksorandom;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //khai báo
    TextView textViewA;
    TextView textViewB;
    EditText editTextKQ;
    Button nutKT;
    int kqDung;
    //Tìm điểu khiển
    void TimDieuKhien(){

        textViewA = (TextView) findViewById(R.id.tvSoA);
        textViewB = (TextView) findViewById(R.id.tvSoB);
        editTextKQ = (EditText) findViewById(R.id.edtKQ);
        nutKT = (Button) findViewById(R.id.btnCheck);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        SinhSoNgauNhien();
        nutKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckKQ();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Sinh số ngẫu nhiên
    void SinhSoNgauNhien(){
        int a = (int) (Math.random()*5);
        int b = (int) (Math.random()*5);
        kqDung = a + b;
        textViewA.setText(String.valueOf(a));
        textViewB.setText(String.valueOf(b));

    }
    // kiểm tra kết quả
    void CheckKQ(){
        // lấy dữ liệu và chuyển chuỗi kq thành số nguyên
        String kqNhap = editTextKQ.getText().toString();
        int chuoiKQ = Integer.parseInt(kqNhap);
        // So sánh với kết quả đúng
        if (chuoiKQ == kqDung){
            Toast.makeText(this, "Chính xác!",LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Sai rồi! Đáp án đúng là " + kqDung,LENGTH_SHORT).show();
        }

    }
}