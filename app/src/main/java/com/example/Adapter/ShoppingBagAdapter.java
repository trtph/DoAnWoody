package com.example.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseUser;
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
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                            .collection("CurrentUser").document(b.getDocumentID())
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
                                    ShoppingCartFragment.SwitchLayout(productshopModels);
                                }
                            });
                }else {
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
                                    ShoppingCartFragment.SwitchLayout(productshopModels);
                                }
                            });
                }

            }
        });
        holder.imvAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCartItem(holder,b);
            }
        });
        holder.imvDecreseNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MinusCartItem(holder,b);
            }
        });
    }

    private void AddCartItem(ViewHolder holder, productshopModel b) {
        int quan = Integer.parseInt(holder.txtQuantity.getText().toString());
        if(quan < 9){
            quan = quan + 1;
            b.setTotalPrice((int) (b.getTotalPrice() + Double.parseDouble(b.getPrPrice().toString().substring(2))));
            ShoppingCartFragment.caculateTotalAmount(productshopModels);
            holder.txtQuantity.setText(String.valueOf(quan));
        }
    }

    private void MinusCartItem(ViewHolder holder, productshopModel b) {
        int quan = Integer.parseInt(holder.txtQuantity.getText().toString());
        int price = b.getTotalPrice();
        if(quan == 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Do you really want to remove this item?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user != null){
                        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                                .collection("CurrentUser").document(b.getDocumentID())
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
                                        ShoppingCartFragment.SwitchLayout(productshopModels);
                                    }
                                });
                    }else {
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
                                        ShoppingCartFragment.SwitchLayout(productshopModels);
                                    }
                                });
                    }

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        if(quan > 1){
            quan = quan - 1;
            b.setTotalPrice((int) (b.getTotalPrice() - Double.parseDouble(b.getPrPrice().toString().substring(2))));
            ShoppingCartFragment.caculateTotalAmount(productshopModels);
            holder.txtQuantity.setText(String.valueOf(quan));
        }
    }

    @Override
    public int getItemCount() {
        return productshopModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb, imvAddNumber, imvDecreseNumber;
        TextView txtInfo, txtPrice, txtQuantity, txtTotal;
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtInfo=itemView.findViewById(R.id.txtInfo);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtQuantity=itemView.findViewById(R.id.txtQuantity);
            imvAddNumber=itemView.findViewById(R.id.imvAddNumber);
            imvDecreseNumber=itemView.findViewById(R.id.imvDecreaseNumber);
            txtTotal=itemView.findViewById(R.id.txtTotal);

            swipeRevealLayout = itemView.findViewById(R.id.SwipeRevealLayout);
            layoutDelete = itemView.findViewById(R.id.layoutDelete);
        }
    }
}
