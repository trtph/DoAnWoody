package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.model.SaleProduct;
import com.example.woodygroupapplication.ProductDetail_Banner;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class SaleProdutcApdater extends RecyclerView.Adapter<SaleProdutcApdater.ViewHolder>{
    Context context;
    int item_sale_product;
    ArrayList<SaleProduct> saleProducts;

    public SaleProdutcApdater(Context context, int item_sale_product, ArrayList<SaleProduct> saleProducts) {
        this.context = context;
        this.item_sale_product = item_sale_product;
        this.saleProducts = saleProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sale_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleProdutcApdater.ViewHolder holder, int position) {
        final SaleProduct s = saleProducts.get(position);
        Glide.with(context).load(s.getPrImage()).into(holder.imvThumb);

        holder.txtName.setText(s.getPrName());
        holder.txtPrice.setText( " $" + s.getPrPrice());
        holder.txtPriceSale.setText(" $" + s.getPrPriceSale());
        holder.ratingBar.setRating(s.getPrRating());

        holder.layout_itemSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickToDetailProductSale(s);
            }
        });
    }

    private void onClickToDetailProductSale(SaleProduct s) {
        Intent intent = new Intent(context, ProductDetail_Banner.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_sale", s);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return saleProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        RatingBar ratingBar;
        TextView txtName, txtPrice,txtPriceSale;
        CardView layout_itemSale;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb1);
            ratingBar = itemView.findViewById(R.id.ratingBar1);
            txtName = itemView.findViewById(R.id.txtName1);
            txtPrice = itemView.findViewById(R.id.txtPrice1);
            txtPriceSale = itemView.findViewById(R.id.txtPriceSale1);
            layout_itemSale = itemView.findViewById(R.id.layout_itemSale);
        }
    }
}
