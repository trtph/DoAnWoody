package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Product;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter2.ViewHolder> {
    Context context;
    ArrayList<Product> products;

    public ProductAdapter2(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_recycler_product, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = products.get(position);
        holder.imvThumbProduct.setImageResource(p.getProductThumb());
        holder.txtName.setText(p.getProductName());
        holder.txtNumber.setText(String.valueOf("$ " + p.getProductNumber()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumbProduct;
        TextView txtName;
        TextView txtNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumbProduct = itemView.findViewById(R.id.imvThumbProduct);
            txtName = itemView.findViewById(R.id.txtName2);
            txtNumber = itemView.findViewById(R.id.txtNumber2);
        }
    }
}
