package ntu.ngokhaidang.checksorandom;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Sinh số ngẫu nhiên
    void SinhSoNgauNhien(){
        int a = (int) Math.random()*5;
        int b = (int) Math.random()*5;
        int kqDung = a + b;
        textViewA.setText(String.valueOf(a));
        textViewB.setText(String.valueOf(b));

    }
}