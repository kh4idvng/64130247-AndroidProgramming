package thigk2.ngokhaidang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KyNiemAdapter extends RecyclerView.Adapter<KyNiemAdapter.ItemKNHolder> {
    Context context;
    ArrayList<KyNiem> lstData;

    public KyNiemAdapter(Context context, ArrayList<KyNiem> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public ItemKNHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater bom = LayoutInflater.from(context);
        View vItem = bom.inflate(R.layout.item_kyniem, parent, false);
        ItemKNHolder vholderCreated = new ItemKNHolder(vItem);
        return vholderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemKNHolder holder, int position) {
        KyNiem kyNiemHienThi = lstData.get(position);

        String caption = kyNiemHienThi.getCaptionAnh();
        String tenAnh = kyNiemHienThi.getTenFileAnh();

        holder.tvCaption.setText(caption);

        String packageName = holder.itemView.getContext().getPackageName();
        int imageID = holder.itemView.getResources().getIdentifier(tenAnh, "mipmap", packageName);

        holder.ivAnhDD.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    class ItemKNHolder extends RecyclerView.ViewHolder {
        TextView tvCaption;
        ImageView ivAnhDD;

        public ItemKNHolder(@NonNull View itemView) {
            super(itemView);
            tvCaption = itemView.findViewById(R.id.textviewCaption);
            ivAnhDD = itemView.findViewById(R.id.imgDaiDien);
        }
    }
}
