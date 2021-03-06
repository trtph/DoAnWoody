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
import com.example.MyInterfaces.IClickItemCollection;
import com.example.model.ProductModel;

import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class ProductCollectionAdapter extends RecyclerView.Adapter<ProductCollectionAdapter.ViewHolder> {

    Context context;
    int item_product_collection;
    ArrayList<ProductModel> product;

    IClickItemCollection iClickItemCollection;

    public ProductCollectionAdapter(Context context, int item_product_collection, ArrayList<ProductModel> product, IClickItemCollection iClickItemCollection) {
        this.context = context;
        this.item_product_collection = item_product_collection;
        this.product = product;
        this.iClickItemCollection = iClickItemCollection;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_collection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductModel p = product.get(position);

        Glide.with(context).load(p.getPrImage()).into(holder.imvThumb);

        holder.txtName.setText(p.getPrName());
        holder.txtPrice.setText( " $" + p.getPrPrice());
        holder.txtRvNumber.setText("(" + p.getPrRvNumber() + ")");
        holder.ratingBar.setRating(p.getPrRating());

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemCollection.onClickItemCollection(p);
            }
        });
    }


    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        RatingBar ratingBar;
        TextView txtName, txtPrice, txtRvNumber;
        CardView layout_item;
        public ViewHolder(@NonNull View itemView) {
          super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtRvNumber = itemView.findViewById(R.id.txtRvNumber);
            layout_item = itemView.findViewById(R.id.layout_item);
        }
    }
}
