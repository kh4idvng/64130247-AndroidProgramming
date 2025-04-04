package thigk2.ngokhaidang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChucNang2 extends AppCompatActivity {
    EditText editTextThang;
    EditText editTextNam;
    Button nutKT;
    void TimDieuKhien(){
        editTextThang = findViewById(R.id.edtThang);
        editTextNam = findViewById(R.id.edtNam);
        nutKT = findViewById(R.id.btnCheck);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang2);
        TimDieuKhien();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nutKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KiemTra();
            }
        });
    }
    public void KiemTra(){
        String thang = editTextThang.getText().toString();
        String nam = editTextNam.getText().toString();

        int Thang = Integer.parseInt(thang);
        int Nam = Integer.parseInt(nam);
        if (( Thang== 4) && ( Nam == 1975)){
            Toast.makeText(ChucNang2.this, "Đúng!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(ChucNang2.this, "Sai!", Toast.LENGTH_SHORT).show();
        }
    }
}