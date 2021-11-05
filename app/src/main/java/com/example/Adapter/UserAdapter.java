package com.example.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.UserAccount;
import com.example.woodygroupapplication.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Activity context;
    List<UserAccount> userAccountList;

    public UserAdapter(Activity context, List<UserAccount> userAccountList) {
        this.context = context;
        this.userAccountList = userAccountList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserAccount b = userAccountList.get(position);
        holder.imvThumb.setImageResource(b.getImage());
        holder.txtName.setText(b.getName());
    }

    @Override
    public int getItemCount() {
        return userAccountList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        TextView txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
        }

    }
}
