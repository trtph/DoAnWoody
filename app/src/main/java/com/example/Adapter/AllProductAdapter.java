package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.model.AllProductModel;
import com.example.model.ProductCollection;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder>{
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
        holder.txtRvNumber.setText("(" + all.getPrRvNumber() + ")");
        holder.ratingBar.setRating(all.getPrRating());
    }

    @Override
    public int getItemCount() {
        return allProductModels.size();
    }

    public void filteredList(ArrayList<AllProductModel> filteredList){
        allProductModels = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        RatingBar ratingBar;
        TextView txtName, txtPrice, txtRvNumber;
        CardView layout_itemProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtRvNumber = itemView.findViewById(R.id.txtRvNumber);
            layout_itemProduct = itemView.findViewById(R.id.layout_itemProduct);
        }
    }
}
