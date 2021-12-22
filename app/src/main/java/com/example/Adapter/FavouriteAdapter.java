package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.model.FavouriteProduct;
import com.example.woodygroupapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    Context context;
    ArrayList<FavouriteProduct> favouriteProducts;
    FirebaseFirestore firestore;

    public FavouriteAdapter(Context context, ArrayList<FavouriteProduct> favouriteProducts) {
        this.context = context;
        this.favouriteProducts = favouriteProducts;
        firestore = FirebaseFirestore.getInstance();
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

        Glide.with(context).load(b.getPrThumb()).into(holder.imvFavouriteThumb);
        holder.txtFavouriteProductName.setText(b.getPrName());
        holder.txtFavouriteProductPrice.setText(b.getPrPrice());
        holder.imvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("AddToWishlist").document(b.getDocumentID())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    favouriteProducts.remove(b);
                                    notifyDataSetChanged();
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
    public int getItemCount() { return favouriteProducts.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvFavouriteThumb, imvRemove;
        TextView txtFavouriteProductName, txtFavouriteProductPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvFavouriteThumb = itemView.findViewById(R.id.imvFavouriteThumb);
            imvRemove = itemView.findViewById(R.id.imvRemove);
            txtFavouriteProductName = itemView.findViewById(R.id.txtFavouriteProductName);
            txtFavouriteProductPrice = itemView.findViewById(R.id.txtFavoriteProductPrice);
        }
    }
}
