package ntu.ngokhaidang.ex5_addsubmuldiv_anynomous;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //khai báo các đói tượng gắn với đkhiển tương ứng ở đây
    EditText editTextSo1;
    EditText editTextSo2;
    EditText editTextKQ;
    Button nutCong, nutTru, nutNhan, nutChia;
    void TimDieuKhien(){

        editTextSo1 = (EditText)findViewById(R.id.edtSo1);
        editTextSo2 = (EditText)findViewById(R.id.edtSo2);
        editTextKQ = (EditText)findViewById(R.id.edtKetQua);
        nutCong = (Button) findViewById(R.id.btnCong);
        nutTru = (Button) findViewById(R.id.btnTru);
        nutNhan = (Button) findViewById(R.id.btnNhan);
        nutChia = (Button) findViewById(R.id.btnChia);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        // Gan bo lang nghe su kien va code xu ly cho tung nut
        View.OnClickListener boLangNgheCong = new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // Code xử lý cộng
                //b1. Lấy dữ liệu 2 số
                //b1.1 Tìm EditText số 1 và số 2
                EditText editTextSo1 = (EditText)findViewById(R.id.edtSo1);
                EditText editTextSo2 = (EditText)findViewById(R.id.edtSo2);
                //b1.2. Lấy dữ liệu từ 2 đkhiển đó

                String soThu1 = editTextSo1.getText().toString();
                String soThu2 = editTextSo2.getText().toString();
                //b1.3. chuyển dữ liệu từ chuỗi sang s
                float soA = Float.parseFloat(soThu1);
                float soB = Float.parseFloat(soThu2);
                //b2. Tính toán
                float Tong = soA + soB;
                // b3. Hiện kết qủa
                //b3.1.
                // b3.2. chuẩn bị dữ liệu xuất
                String chuoiKQ = String.valueOf(Tong);
                //B3.3 gắn kết quả lên đk
                editTextKQ.setText(chuoiKQ);
            }
        };
        nutCong.setOnClickListener(boLangNgheCong);
        nutTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code xử lý trừ
                // Code xử lý cộng
                //b1. Lấy dữ liệu 2 số
                //b1.1 Tìm EditText số 1 và số 2
                //b1.2. Lấy dữ liệu từ 2 đkhiển đó

                String soThu1 = editTextSo1.getText().toString();
                String soThu2 = editTextSo2.getText().toString();
                //b1.3. chuyển dữ liệu từ chuỗi sang s
                float soA = Float.parseFloat(soThu1);
                float soB = Float.parseFloat(soThu2);
                //b2. Tính toán
                float Hieu = soA - soB;
                // b3. Hiện kết qủa
                //b3.1.
                // b3.2. chuẩn bị dữ liệu xuất
                String chuoiKQ = String.valueOf(Hieu);
                //B3.3 gắn kết quả lên đk
                editTextKQ.setText(chuoiKQ);

            }
        });

        nutNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code xử lý nhân
                // Code xử lý cộng
                //b1. Lấy dữ liệu 2 số
                //b1.1 Tìm EditText số 1 và số 2
                //b1.2. Lấy dữ liệu từ 2 đkhiển đó

                String soThu1 = editTextSo1.getText().toString();
                String soThu2 = editTextSo2.getText().toString();
                //b1.3. chuyển dữ liệu từ chuỗi sang s
                float soA = Float.parseFloat(soThu1);
                float soB = Float.parseFloat(soThu2);
                //b2. Tính toán
                float Tich = soA * soB;
                // b3. Hiện kết qủa
                //b3.1.
                // b3.2. chuẩn bị dữ liệu xuất
                String chuoiKQ = String.valueOf(Tich);
                //B3.3 gắn kết quả lên đk
                editTextKQ.setText(chuoiKQ);
            }
        });

        nutChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code xử lý chia
                // Code xử lý cộng
                //b1. Lấy dữ liệu 2 số
                //b1.1 Tìm EditText số 1 và số 2
                //b1.2. Lấy dữ liệu từ 2 đkhiển đó

                String soThu1 = editTextSo1.getText().toString();
                String soThu2 = editTextSo2.getText().toString();
                //b1.3. chuyển dữ liệu từ chuỗi sang s
                float soA = Float.parseFloat(soThu1);
                float soB = Float.parseFloat(soThu2);
                //b2. Tính toán
                float Thuong = soA / soB;
                // b3. Hiện kết qủa
                //b3.1.
                // b3.2. chuẩn bị dữ liệu xuất
                String chuoiKQ = String.valueOf(Thuong);
                //B3.3 gắn kết quả lên đk
                editTextKQ.setText(chuoiKQ);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}