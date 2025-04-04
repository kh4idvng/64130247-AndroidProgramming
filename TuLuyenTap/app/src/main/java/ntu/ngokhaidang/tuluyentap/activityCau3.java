package ntu.ngokhaidang.tuluyentap;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;

public class activityCau3 extends AppCompatActivity {
    PhongCanhAdapter phongCanhAdapter;
    ArrayList<PhongCanh> recyclerViewDatas;
    RecyclerView recyclerViewPhongCanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau3);
        recyclerViewDatas = getDataForRecyclerView();

        recyclerViewPhongCanh = findViewById(R.id.recyclerPC);

        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        recyclerViewPhongCanh.setLayoutManager(layoutLinear);

        phongCanhAdapter = new PhongCanhAdapter(this, recyclerViewDatas);

        recyclerViewPhongCanh.setAdapter(phongCanhAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    ArrayList<PhongCanh> getDataForRecyclerView(){
        ArrayList<PhongCanh> dsDuLieu = new ArrayList<PhongCanh>();
        PhongCanh phongCanh1 = new PhongCanh("sea", "Biển Nha Trang");
        dsDuLieu.add(phongCanh1);
        dsDuLieu.add(new PhongCanh("sky", "Bầu trời"));
        dsDuLieu.add(new PhongCanh("field", "Đồng lúa Diên Khánh"));
        return dsDuLieu;

    }
}