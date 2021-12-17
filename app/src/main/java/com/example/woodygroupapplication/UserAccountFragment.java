package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class UserAccountFragment extends Fragment {

    Button btnLogin;
    LinearLayout linearMyPurchase,linearRecentlyView,linearWishList,linearMyVoucher,linearAccountSetting,linearChatWithWoody;

//    RecyclerView rcvUserAccount;
//    List<UserAccount> userAccounts;
//    UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_account, container, false);

        //linkViews
        btnLogin = view.findViewById(R.id.btnLogin);

        linearMyPurchase = view.findViewById(R.id.linearMypurchase);
        linearRecentlyView = view.findViewById(R.id.linearRecentlyView);
        linearWishList = view.findViewById(R.id.linearWishList);
        linearMyVoucher = view.findViewById(R.id.linearMyVoucher);
        linearAccountSetting = view.findViewById(R.id.linearAccountSetting);
        linearChatWithWoody = view.findViewById(R.id.linearChatWithWoody);

        //addEvents
//        rcvUserAccount = view.findViewById(R.id.rcvUserAccounts);

//        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        rcvUserAccount.setLayoutManager(manager);
//
//        DividerItemDecoration decoration = new DividerItemDecoration(rcvUserAccount.getContext(),DividerItemDecoration.VERTICAL);
//        rcvUserAccount.addItemDecoration(decoration);
//
//        userAccounts = new ArrayList<UserAccount>();
//        userAccounts.add(new UserAccount("My purchase",R.drawable.mypurchase));
//        userAccounts.add(new UserAccount("Recently view",R.drawable.recentlyview));
//        userAccounts.add(new UserAccount("Wish list",R.drawable.wishlist));
//        userAccounts.add(new UserAccount("My vouchers",R.drawable.myvouchers));
//        userAccounts.add(new UserAccount("Chat with woody",R.drawable.chatwithwoody));
//
//        userAdapter = new UserAdapter((Activity) getContext(),userAccounts);
//
//        rcvUserAccount.setAdapter(userAdapter);

        //Open Login Activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        //Open My Purchase tab
        linearMyPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //Open Recently View tab
        linearRecentlyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //Open Wish List tab
        linearWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FavoriteFragment.class));
            }
        });
        //Open My Voucher tab
        linearMyVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyVouchers.class));
            }
        });
        //Open Account Setting tab
        linearAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AccountSetting.class));
            }
        });
        //Open Chat With Woody tab
        linearChatWithWoody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

}