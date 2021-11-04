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
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class ProductApdater extends RecyclerView.Adapter<ProductApdater.ViewHolder> {
    Context context;
    ArrayList<Product> products;

    public ProductApdater(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = products.get(position);
        holder.imvThumb.setImageResource(p.getProductThumb());
        holder.txtName.setText(p.getProductName());
        holder.txtNumber.setText(String.valueOf(p.getProductNumber())+" items");

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imvThumb;
        TextView txtName;
        TextView txtNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
        }
    }
}
