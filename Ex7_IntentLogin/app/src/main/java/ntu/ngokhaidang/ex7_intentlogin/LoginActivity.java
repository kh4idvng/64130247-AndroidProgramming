package ntu.ngokhaidang.ex7_intentlogin;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnXacNhan = (Button) findViewById(R.id.btnOK);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xu ly dang nhap
                // lay du lieu
                //B1. tim tham chieu dk
                EditText edTenDN = (EditText) findViewById(R.id.edtUsername);
                EditText edPass = (EditText) findViewById(R.id.edtPassword);
                //B2. lay du lieu
                String tenDangNhap = edTenDN.getText().toString();
                String mk = edPass.getText().toString();
                //Kiem tra mat khau
                if (tenDangNhap.equals("ngokhaidang") && mk.equals("123")) // mk dung
                {
                    //chuyen sang man hinh home
                    Intent iQuiz = new Intent(LoginActivity.this, HomeActivity.class);
                    //Goi du lieu vao iQuiz, dang key - value; key duoc dung de ben kia loc ra du lieu
                    iQuiz.putExtra("ten_dang_nhap", tenDangNhap);
                    iQuiz.putExtra("mk_dang_nhap", mk);
                    //gui di
                    startActivity(iQuiz);

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "BẠN NHẬP SAI THÔNG TIN",Toast.LENGTH_LONG);

                }

            }
        });
    }
}