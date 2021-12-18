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

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.model.productshopModel;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class ShoppingBagAdapter extends RecyclerView.Adapter<ShoppingBagAdapter.ViewHolder> {

    Context context;
    ArrayList<productshopModel> productshopModels;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public ShoppingBagAdapter (Context context,  ArrayList<productshopModel> productshopModels){
        this.context=context;
        this.productshopModels=productshopModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.shoppingbag_items,parent,false);
        //       View view = inflater.inflate(R.layout.item_recyclerview,null);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        productshopModel b = productshopModels.get(position);
        holder.imvThumb.setImageResource(productshopModels.get(position).getProductThumb());
        holder.txtInfo.setText(b.getProductName());
        holder.txtPrice.setText(String.valueOf(b.getProductPrice()));
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(b));
        viewBinderHelper.setOpenOnlyOne(true);

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productshopModels.remove(holder.getAbsoluteAdapterPosition());
                notifyItemRemoved(holder.getAbsoluteAdapterPosition());
                caculateCart();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productshopModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        TextView txtInfo, txtPrice, txtTotal;
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtInfo=itemView.findViewById(R.id.txtInfo);
            txtPrice=itemView.findViewById(R.id.txtPrice);

            swipeRevealLayout = itemView.findViewById(R.id.SwipeRevealLayout);
            layoutDelete = itemView.findViewById(R.id.layoutDelete);
        }
    }
    public Double caculateCart() {
        double total = 0;
        for (int i = 0; i < productshopModels.size(); i++) {
            total = total + (productshopModels.get(i).getProductPrice());
        }
        return total;
    }
}
