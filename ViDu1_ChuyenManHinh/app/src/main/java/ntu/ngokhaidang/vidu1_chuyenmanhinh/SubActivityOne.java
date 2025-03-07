package ntu.ngokhaidang.vidu1_chuyenmanhinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivityOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_one);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //Ham dap ung su kien nhan len nut "Quay ve trang chu"
    //Xu ly chuyen ve man hinh trang chu
    public void ChuyenManHinh(View view){
        //Tao mot doi tuong Intent
        //Tham so thu 2 cua ham tao nay, la ten Activity (man hinh) ta muon chuyen sang
        Intent iManHinhCHINH = new Intent(this, MainActivity.class);
        //thuc hien chuyen
        startActivity(iManHinhCHINH);
    }
}