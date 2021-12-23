package com.example.woodygroupapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class UserAccountFragment extends Fragment {
    private View view;

    Button btnLogin, btnLogout;
    TextView txtName, txtEmail;
    ImageView imvAvatar;
    LinearLayout linearMyPurchase, linearRecentlyView,
            linearMyVoucher, linearAccountSetting, linearChat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_account, container, false);

        linkViews();
        OnClickListeners();
        addEvents();
        showUserInformation();

        return view;
    }

    private void linkViews() {
        //linkViews
        btnLogin = view.findViewById(R.id.btnLogin);
        btnLogout = view.findViewById(R.id.btnLogout);
        linearMyPurchase = view.findViewById(R.id.linearMyPurchase);
        linearRecentlyView = view.findViewById(R.id.linearRecentlyView);
        linearMyVoucher = view.findViewById(R.id.linearMyVoucher);
        linearAccountSetting = view.findViewById(R.id.linearAccountSetting);
        linearChat = view.findViewById(R.id.linearChat);
        txtName = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtEmail);
        imvAvatar = view.findViewById(R.id.imvAvatar);
    }

    private void OnClickListeners() {
        //Open Login Activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        //Open Logout Activity
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //Open My Purchase ---> unfulfilled
        linearMyPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewFeatureActivity.class);
                startActivity(intent);
            }
        });

        //Open Recently View ---> unfulfilled
        linearMyPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewFeatureActivity.class);
                startActivity(intent);
            }
        });

        //Open My Voucher Activity
        linearMyVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyVouchers.class));
            }
        });

        //Open AccountSetting
        linearAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AccountSetting.class);
                startActivity(intent);
            }
        });

        //Open Chat With Woody ---> unfulfilled
        linearChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewFeatureActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addEvents() {
        //Check LogIn
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            btnLogin.setVisibility(View.INVISIBLE);
        }else {
            btnLogout.setVisibility(View.INVISIBLE);
        }
    }

    public  void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if (name == null){
            txtName.setVisibility(View.INVISIBLE);
        }else {
            txtName.setVisibility(View.VISIBLE);
            txtName.setText(name);
        }
        txtEmail.setText(email);

        Glide.with(getContext()).load(photoUrl).error(R.drawable.ic_account_default).into(imvAvatar);
    }


}