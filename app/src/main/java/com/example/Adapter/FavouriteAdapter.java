package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.FavouriteProduct;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    Context context;
    ArrayList<FavouriteProduct> favouriteProducts;

    public FavouriteAdapter(Context context, ArrayList<FavouriteProduct> favouriteProducts) {
        this.context = context;
        this.favouriteProducts = favouriteProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_favouritefragment_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavouriteProduct b = favouriteProducts.get(position);
        holder.imvFavouriteThumb.setImageResource(favouriteProducts.get(position).getProductThumb());
        holder.txtFavouriteProductName.setText(b.getProductName());
        holder.txtFavouriteProductPrice.setText(String.valueOf(b.getProductPrice()));
    }

    @Override
    public int getItemCount() { return favouriteProducts.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvFavouriteThumb;
        TextView txtFavouriteProductName, txtFavouriteProductPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvFavouriteThumb = itemView.findViewById(R.id.imvFavouriteThumb);
            txtFavouriteProductName = itemView.findViewById(R.id.txtFavouriteProductName);
            txtFavouriteProductPrice = itemView.findViewById(R.id.txtFavoriteProductPrice);
        }
    }
}
