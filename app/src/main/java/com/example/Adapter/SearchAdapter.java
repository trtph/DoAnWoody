package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.searchProduct;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    ArrayList<searchProduct> searchProducts;

    public SearchAdapter(Context context, ArrayList<searchProduct> searchProducts) {
        this.context = context;
        this.searchProducts = searchProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imvThumb.setImageResource(searchProducts.get(position).getProductThumb());
        holder.txtInfo.setText(searchProducts.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return searchProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imvThumb;
        TextView txtInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtInfo = itemView.findViewById(R.id.txtInfo);
        }
    }
}
