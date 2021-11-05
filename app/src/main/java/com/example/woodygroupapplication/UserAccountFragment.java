package com.example.woodygroupapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.UserAdapter;
import com.example.model.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class UserAccountFragment extends Fragment {

    Button btnLogin;

    RecyclerView rcvUserAccount;
    List<UserAccount> userAccounts;
    UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_account, container, false);

        //linkViews
        btnLogin = view.findViewById(R.id.btnLogin);

        //addEvents
        rcvUserAccount = view.findViewById(R.id.rcvUserAccounts);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvUserAccount.setLayoutManager(manager);

        DividerItemDecoration decoration = new DividerItemDecoration(rcvUserAccount.getContext(),DividerItemDecoration.VERTICAL);
        rcvUserAccount.addItemDecoration(decoration);

        userAccounts = new ArrayList<UserAccount>();
        userAccounts.add(new UserAccount("My purchase",R.drawable.mypurchase));
        userAccounts.add(new UserAccount("Recently view",R.drawable.recentlyview));
        userAccounts.add(new UserAccount("Wish list",R.drawable.wishlist));
        userAccounts.add(new UserAccount("My vouchers",R.drawable.myvouchers));
        userAccounts.add(new UserAccount("Chat with woody",R.drawable.chatwithwoody));

        userAdapter = new UserAdapter((Activity) getContext(),userAccounts);

        rcvUserAccount.setAdapter(userAdapter);

        //Open Login Activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        return view;
    }


}