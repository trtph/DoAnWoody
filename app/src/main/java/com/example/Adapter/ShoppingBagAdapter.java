package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.model.productshopModel;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class ShoppingBagAdapter extends RecyclerView.Adapter<ShoppingBagAdapter.ViewHolder> {

    Context context;
    ArrayList<productshopModel> productshopModels;
    int totalPrice = 0;
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

        Glide.with(context).load(b.getPrThumb()).into(holder.imvThumb);
        holder.txtInfo.setText(b.getPrName());
        holder.txtPrice.setText(b.getPrPrice());
        holder.txtQuantity.setText(b.getTotalQuantity());

        //pass total amount to My Cart Fragment
        totalPrice = totalPrice + b.getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount", totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(b));
        viewBinderHelper.setOpenOnlyOne(true);

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productshopModels.remove(holder.getAbsoluteAdapterPosition());
                notifyItemRemoved(holder.getAbsoluteAdapterPosition());
//                caculateCart();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productshopModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        TextView txtInfo, txtPrice, txtQuantity;
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtInfo=itemView.findViewById(R.id.txtInfo);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtQuantity=itemView.findViewById(R.id.txtQuantity);

            swipeRevealLayout = itemView.findViewById(R.id.SwipeRevealLayout);
            layoutDelete = itemView.findViewById(R.id.layoutDelete);
        }
    }
//    public String caculateCart() {
//        String total = 0;
//        for (int i = 0; i < productshopModels.size(); i++) {
//            total = total + (productshopModels.get(i).getPrPrice());
//        }
//        return total;
//    }
}
