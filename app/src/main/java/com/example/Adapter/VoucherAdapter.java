package com.example.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Voucher;
import com.example.woodygroupapplication.R;

import java.util.List;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.ViewHolder> {
    Activity context;
    List<Voucher> voucherList;

    public VoucherAdapter(Activity context, List<Voucher> voucherList) {
        this.context = context;
        this.voucherList = voucherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
<<<<<<< HEAD:app/src/main/java/com/example/Adapter/UserAdapter.java
        View view = inflater.inflate(R.layout.item_vouchers,parent,false);
=======
        View view = inflater.inflate(R.layout.item_voucher,parent,false);
>>>>>>> master:app/src/main/java/com/example/Adapter/VoucherAdapter.java
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Voucher b = voucherList.get(position);
        holder.imvThumb.setImageResource(b.getImageVoucher());
        holder.txtName.setText(b.getNameVoucher());
        holder.txtContentVoucher.setText((b.getContentVoucher()));

    }

    @Override
    public int getItemCount() {
        return voucherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        TextView txtName,txtContentVoucher;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
            txtContentVoucher = itemView.findViewById(R.id.txtContentVoucher);
        }
    }
}
