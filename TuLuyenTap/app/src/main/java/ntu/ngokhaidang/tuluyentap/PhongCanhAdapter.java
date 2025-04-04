package ntu.ngokhaidang.tuluyentap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhongCanhAdapter extends RecyclerView.Adapter<PhongCanhAdapter.ItemPCHolder> {
    Context context;
    ArrayList<PhongCanh> lstData;

    public PhongCanhAdapter(Context context, ArrayList<PhongCanh> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public ItemPCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater bom = LayoutInflater.from(context);
        View vItem = bom.inflate(R.layout.item_pc, parent,false);
        ItemPCHolder vholderCreated = new ItemPCHolder(vItem);
        return vholderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPCHolder holder, int position) {
        PhongCanh phongCanhHienThi = lstData.get(position);

        String caption = phongCanhHienThi.getCaptionAnh();
        String tenAnh = phongCanhHienThi.getTenFileAnh();

        holder.tvCaption.setText(caption);

        String packageName = holder.itemView.getContext().getPackageName();
        int imageID = holder.itemView.getResources().getIdentifier(tenAnh,"mipmap", packageName);

        holder.ivAnhPC.setImageResource(imageID);


    }
    @Override
    public int getItemCount() {
        return lstData.size();
    }

    class ItemPCHolder extends RecyclerView.ViewHolder{
        TextView tvCaption;
        ImageView ivAnhPC;

        public ItemPCHolder(@NonNull View itemView) {
            super(itemView);
            tvCaption = itemView.findViewById(R.id.textviewCaption);
            ivAnhPC = itemView.findViewById(R.id.imgPC);
        }
    }
}
