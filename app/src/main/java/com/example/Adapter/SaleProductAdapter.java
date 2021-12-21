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

public class SaleProductAdapter extends RecyclerView.Adapter<SaleProductAdapter.ViewHolder>{
    Context context;
    int item_sale_product;
    ArrayList<ProductModel> product;

    IClickItemCollection iClickItemCollection;

    public SaleProductAdapter(Context context, int item_sale_product, ArrayList<ProductModel> product, IClickItemCollection iClickItemCollection) {
        this.context = context;
        this.item_sale_product = item_sale_product;
        this.product = product;
        this.iClickItemCollection = iClickItemCollection;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_discount, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleProductAdapter.ViewHolder holder, int position) {
        final ProductModel p = product.get(position);
        Glide.with(context).load(p.getPrImage()).into(holder.imvThumb);

        holder.txtName.setText(p.getPrName());
        holder.txtPrice.setText( " $" + p.getPrPrice());
        holder.txtPriceSale.setText(" $" + p.getPrPriceSale());
        holder.ratingBar.setRating(p.getPrRating());

        holder.layout_itemSale.setOnClickListener(new View.OnClickListener() {
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
