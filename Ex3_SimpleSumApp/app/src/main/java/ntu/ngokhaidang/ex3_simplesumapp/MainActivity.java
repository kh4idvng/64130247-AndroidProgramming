package ntu.ngokhaidang.ex3_simplesumapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edtA), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //bo lang nghe va xu ly su kien len nut tinh tong
    public void XuLyCong(View view){
        //Tim , tham chieu bien den dieu khien tren tep XML
        EditText editTextSoA = findViewById(R.id.edtA);
        EditText editTextSoB = findViewById(R.id.edtB);
        EditText editTextKetQua = findViewById(R.id.edtKQ);
        //lay du lieu ve o dieu khien A
        String strA = editTextSoA.getText().toString();
        //lay du lieu ve o dieu khien B
        String strB = editTextSoB.getText().toString();

        //chuyen du lieu sang dang so
        int so_A = Integer.parseInt(strA);
        int so_B = Integer.parseInt(strB);

        //Tinh toan theo yeu cau
        int tong = so_A + so_B;
        String strTong = String.valueOf(tong);

        //Hien ra man hinh
        editTextKetQua.setText(strTong);




    }
}