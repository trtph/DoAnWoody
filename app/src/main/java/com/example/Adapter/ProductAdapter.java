package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MyInterfaces.IClickItemCollection;
import com.example.model.ProductModel;

import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    int item_product;
    ArrayList<ProductModel> product;

    IClickItemCollection iClickItemCollection;

    public ProductAdapter(Context context, int item_product, ArrayList<ProductModel> product, IClickItemCollection iClickItemCollection) {
        this.context = context;
        this.item_product = item_product;
        this.product = product;
        this.iClickItemCollection = iClickItemCollection;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        ProductModel p = product.get(position);

        Glide.with(context).load(p.getPrImage()).into(holder.imvThumb);
        holder.txtName.setText(p.getPrName());
        holder.txtPrice.setText( " $" + p.getPrPrice());

        //OnClick Item
        holder.layout_itemProduct.setOnClickListener(new View.OnClickListener() {
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
    public void filterList(ArrayList<ProductModel> filteredList){
        product = filteredList;
        notifyDataSetChanged();
    }


}
