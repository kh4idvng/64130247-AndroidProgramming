package ntu.ngokhaidang.tuluyentap;

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

    Button nut1,nut2,nut3,nut4;

    void TimDieuKhien(){
        nut1 = findViewById(R.id.btnCau1);
        nut2 = findViewById(R.id.btnCau2);
        nut3 = findViewById(R.id.btnCau3);
        nut4 = findViewById(R.id.btnCau4);
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
        nut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity1 = new Intent(MainActivity.this, activityCau1.class);
                startActivity(Activity1);
            }
        });
        nut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity2 = new Intent(MainActivity.this, activityCau2.class);
                startActivity(Activity2);
            }
        });
        nut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity3 = new Intent(MainActivity.this, activityCau3.class);
                startActivity(Activity3);
            }
        });
        nut4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity4 = new Intent(MainActivity.this, activityCau4.class);
                startActivity(Activity4);
            }
        });
    }
}