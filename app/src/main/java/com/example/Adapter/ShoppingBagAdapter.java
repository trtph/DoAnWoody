package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.model.productshopModel;
import com.example.woodygroupapplication.R;
import com.example.woodygroupapplication.ShoppingCartFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ShoppingBagAdapter extends RecyclerView.Adapter<ShoppingBagAdapter.ViewHolder> {

    Context context;
    ArrayList<productshopModel> productshopModels;
    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public ShoppingBagAdapter (Context context,  ArrayList<productshopModel> productshopModels){
        this.context=context;
        this.productshopModels=productshopModels;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
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



        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(b));
        viewBinderHelper.setOpenOnlyOne(true);

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("AddToCart").document(b.getDocumentID())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    productshopModels.remove(b);
                                    notifyDataSetChanged();
                                    ShoppingCartFragment.caculateTotalAmount(productshopModels);
                                    Toast.makeText(context, "Remove Item Successful", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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
}
