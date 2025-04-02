package ntu.ngokhaidang.tuluyentap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activityCau1 extends AppCompatActivity {

    EditText editTextSoA;
    EditText editTextSoB;
    Button nutCong;
    EditText editTextKQ;
    void TimDieuKhien(){
        editTextSoA = findViewById(R.id.edtSoA);
        editTextSoB = findViewById(R.id.edtSoB);
        editTextKQ = findViewById(R.id.edtKQ);
        nutCong = findViewById(R.id.btnTong);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau1);
        TimDieuKhien();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nutCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XULY_CONG();
            }
        });

    }
    void XULY_CONG(){
        String so1 = editTextSoA.getText().toString();
        String so2 = editTextSoB.getText().toString();
        float num1 = Float.parseFloat(so1);
        float num2 = Float.parseFloat(so2);
        float tong = num1 + num2;
        String chuoiKQ = String.valueOf(tong);
        editTextKQ.setText(chuoiKQ);
    }
}