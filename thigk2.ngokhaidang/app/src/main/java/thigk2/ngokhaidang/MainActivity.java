package thigk2.ngokhaidang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button nut2;
    Button nut3;
    Button nut4;
    Button nutAM;
    void TimDieuKhien(){
        nut2 = findViewById(R.id.btnCN2);
        nut3 = findViewById(R.id.btnCN3);
        nut4 = findViewById(R.id.btnCN4);
        nutAM = findViewById(R.id.btnAM);
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
        nut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CN2 = new Intent(MainActivity.this, ChucNang2.class);
                startActivity(CN2);
            }
        });
        nut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CN3 = new Intent(MainActivity.this, ChucNang3.class);
                startActivity(CN3);
            }
        });
        nutAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AM = new Intent(MainActivity.this, TTCaNhan.class);
                startActivity(AM);
            }
        });
        nut4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CN4 = new Intent(MainActivity.this, ChucNang4.class);
                startActivity(CN4);
            }
        });
    }
}