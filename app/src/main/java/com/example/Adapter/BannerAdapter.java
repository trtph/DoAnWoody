package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.model.Banner;
import com.example.woodygroupapplication.BlackFridayActivity;
import com.example.woodygroupapplication.R;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder>{

    Context context;
    ArrayList<Banner> bannersList;

    public BannerAdapter(Context context, ArrayList<Banner> bannersList) {
        this.context = context;
        this.bannersList = bannersList;
    }

    @NonNull
    @Override
    public BannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //ImageView: Glide Library
        Glide.with(context).load(bannersList.get(position).getImageUrl()).into(holder.imvBanner);

        //GoToBlackFriday
        holder.lvBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBlackFriday();
            }
        });

    }

    private void onClickBlackFriday() {
        Intent intent = new Intent(context, BlackFridayActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return bannersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvBanner;
        LinearLayout lvBanner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Link View
            imvBanner = itemView.findViewById(R.id.imvBanner);
            lvBanner =itemView.findViewById(R.id.lvbanner);
        }
    }
}
