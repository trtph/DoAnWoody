package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.model.Collection;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder>{

    Context context;
    ArrayList<Collection> collections;

    public CollectionAdapter(Context context, ArrayList<Collection> collections) {
        this.context = context;
        this.collections = collections;
    }


    @NonNull
    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycler_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.ViewHolder holder, int position) {

        holder.txtName.setText(collections.get(position).getCltName());
        holder.txtNumber.setText(String.valueOf(collections.get(position).getCltNumber()));

        //ImageView: Glide Library
        Glide.with(context).load(collections.get(position).getCltImage()).into(holder.imvThumb);

    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imvThumb;
        TextView txtName;
        TextView txtNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
        }
    }
}
