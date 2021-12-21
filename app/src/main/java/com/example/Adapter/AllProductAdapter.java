package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.model.AllProductModel;

import com.example.woodygroupapplication.ProductDetailActivity;
import com.example.woodygroupapplication.ProductDetail_Collection;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {
    Context context;
    int item_product;
    ArrayList<AllProductModel> allProductModels;

    public AllProductAdapter(Context context, int item_product, ArrayList<AllProductModel> allProductModels) {
        this.context = context;
        this.item_product = item_product;
        this.allProductModels = allProductModels;
    }

    @NonNull
    @Override
    public AllProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolder holder, int position) {
        AllProductModel all = allProductModels.get(position);

        Glide.with(context).load(all.getPrImage()).into(holder.imvThumb);
        holder.txtName.setText(all.getPrName());
        holder.txtPrice.setText( " $" + all.getPrPrice());

        //OnClick Item
        holder.layout_itemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickToDetail(all);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allProductModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        TextView txtName, txtPrice;
        LinearLayout layout_itemProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            layout_itemProduct = itemView.findViewById(R.id.layout_itemProduct);
        }
    }
    //Search view
    public void filterList(ArrayList<AllProductModel> filteredList){
        allProductModels = filteredList;
        notifyDataSetChanged();
    }

    //Open Product Detail
    private void onClickToDetail(AllProductModel all){
        Intent intent = new Intent(context, ProductDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", all);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void release(){
        context = null;
    }
}
