package ntu.ngokhaidang.tuluyentap;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class activityCau2 extends AppCompatActivity {
    ListView listViewDSNhac;
    ArrayList<String> listDSNhac;
    ArrayAdapter<String> adapterDSNhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewDSNhac = findViewById(R.id.lvNhac);
        listDSNhac = new ArrayList<String>();
        listDSNhac.add("My Jinji");
        listDSNhac.add("Moonlight On The River");
        listDSNhac.add("Em Trang Tri");

        adapterDSNhac = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listDSNhac);
        listViewDSNhac.setAdapter(adapterDSNhac);

        listViewDSNhac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String giatriDuocChon = listDSNhac.get(position);

                Toast.makeText(activityCau2.this, giatriDuocChon, Toast.LENGTH_SHORT).show();
            }
        });
    }
}