package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.ListProduct;
//import com.example.model.Product;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ViewHolder> {
    Context context;
    ArrayList<ListProduct> listProducts;

    public ListProductAdapter(Context context, ArrayList<ListProduct> listProducts) {
        this.context = context;
        this.listProducts = listProducts;

    }

    @NonNull
    @Override
    public ListProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_listviewproduct, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductAdapter.ViewHolder holder, int position) {
        ListProduct p = listProducts.get(position);
        holder.imvThumb2.setImageResource(p.getProductThumb2());
        holder.txtName2.setText(p.getProductName2());
        holder.txtPrice2.setText(String.valueOf("$ " + p.getProductPrice2()));
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb2;
        TextView txtName2;
        TextView txtPrice2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb2 = itemView.findViewById(R.id.imvThumb2);
            txtName2 = itemView.findViewById(R.id.txtName2);
            txtPrice2 = itemView.findViewById(R.id.txtPrice2);
        }
    }
}
