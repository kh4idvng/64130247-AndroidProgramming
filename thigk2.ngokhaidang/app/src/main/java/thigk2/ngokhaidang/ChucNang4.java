package thigk2.ngokhaidang;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChucNang4 extends AppCompatActivity {
    KyNiemAdapter kyNiemAdapter;
    ArrayList<KyNiem> recyclerViewDatas;
    RecyclerView recyclerViewKyNiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang4);
        recyclerViewDatas = getDataForRecyclerView();

        recyclerViewKyNiem = findViewById(R.id.recyclerKN);

        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        recyclerViewKyNiem.setLayoutManager(layoutLinear);

        kyNiemAdapter = new KyNiemAdapter(this, recyclerViewDatas);

        recyclerViewKyNiem.setAdapter(kyNiemAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    ArrayList<KyNiem> getDataForRecyclerView(){
        ArrayList<KyNiem> dsDuLieu = new ArrayList<KyNiem>();
        KyNiem kyNiem1 = new KyNiem("dieubinh304", "Diễu Binh 30/4");
        dsDuLieu.add(kyNiem1);
        dsDuLieu.add(new KyNiem("mitinhchaomung", "Mít Tinh Chào Mừng"));
        dsDuLieu.add(new KyNiem("quandoi3004", "Hoạt Động 30/4 của quân đội"));
        return dsDuLieu;

    }
}