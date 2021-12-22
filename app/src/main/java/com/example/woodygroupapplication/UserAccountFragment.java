package com.example.woodygroupapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAccountFragment extends Fragment {

    Button btnLogin;
    TextView txtUserName;
    ImageView imgAvatar;
    LinearLayout linearMyPurchase, linearRecentlyView, linearWishList, linearMyVoucher, linearAccountSetting, linearChatWithWoody;

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
        txtUserName = view.findViewById(R.id.txtUserName);
        imgAvatar = view.findViewById(R.id.imageView);

        //Open Login Activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        //Open My Voucher tab
        linearMyVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyVouchers.class));
            }
        });

//        //Open Account Setting tab
//        linearAccountSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    Intent intent = new Intent(getContext(), AccountSettingFragment.class);
//                    startActivity(intent);
//                } catch (Exception ex) {
//                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
//
//                }
//
//            }
//        });


        //Open Chat With Woody tab
        linearChatWithWoody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewFeatureActivity.class);
                UserAccountFragment.this.startActivity(intent);
            }
        });


        View.OnClickListener myClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = null;

                    if (view.getId() == R.id.linearMypurchase) {
                        fragment = new MyPurchasesFragment();
                    } else if (view.getId() == R.id.linearRecentlyView) {
                        fragment = new RecentlyViewFragment();
                    } else if (view.getId() == R.id.linearWishList) {
                        fragment = new FavoriteFragment();
                    } else if (view.getId() == R.id.linearAccountSetting) {
                        fragment = new AccountSettingFragment();

                    }
                    if (fragment != null) {
                        fragmentTransaction.replace(R.id.frame_content, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_SHORT).show();
                }


            }

            ;
        };

        //Open My Purchase tab
        linearMyPurchase.setOnClickListener(myClick);

        //Open Recently View tab
        linearRecentlyView.setOnClickListener(myClick);

        //Open Wish List tab
        linearWishList.setOnClickListener(myClick);

        //Open Account Setting tab
        linearAccountSetting.setOnClickListener(myClick);

        showUserInformation();
        return view;
    }

    private void showUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if (name == null){
            txtUserName.setVisibility(View.GONE);
        }else {
            txtUserName.setVisibility(View.VISIBLE);
            txtUserName.setText(name);
        }
//        imgAvatar.setImageBitmap(photoUrl);
//        Glide.with(this).load(user.getPhotoUrl()).error(R.drawable.account).into(imgAvatar);
//        imgAvatar.get(photoUrl);

    }


}