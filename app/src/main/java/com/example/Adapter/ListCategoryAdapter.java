package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.ListCategoryProduct;
import com.example.model.searchProduct;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class ListCategoryAdapter extends  RecyclerView.Adapter<ListCategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<ListCategoryProduct> listCategoryProducts;

    public ListCategoryAdapter(Context context, ArrayList<ListCategoryProduct> listCategoryProducts) {
        this.context = context;
        this.listCategoryProducts = listCategoryProducts;
    }

    @NonNull
    @Override
    public ListCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_collection_of_category, parent, false);
        //       View view = inflater.inflate(R.layout.item_recyclerview,null);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCategoryAdapter.ViewHolder holder, int position) {

        holder.imvThumb1.setImageResource(listCategoryProducts.get(position).getProductThumb1());
        holder.txtInfo1.setText(listCategoryProducts.get(position).getProductName1());

    }

    @Override
    public int getItemCount() {
        return listCategoryProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imvThumb1;
        TextView txtInfo1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb1 = itemView.findViewById(R.id.imvThumb1);
            txtInfo1 = itemView.findViewById(R.id.txtInfo1);
        }
    }
}

