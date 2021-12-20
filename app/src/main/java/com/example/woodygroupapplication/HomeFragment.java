package com.example.woodygroupapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.AllProductAdapter;
import com.example.Adapter.BannerAdapter;
import com.example.model.AllProductModel;
import com.example.model.Banner;
import com.example.model.ProductCollection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    ImageView imvBest, imvSeat, imvDesk, imvSofa, imvBed, imvNoti;
    Button btnShowroom, btn3D;
    FrameLayout frame_collection;
    RecyclerView rcvBanner;

    //Firebase
    DatabaseReference databaseReference;

    //Variable
    ArrayList<Banner> bannersList;
    BannerAdapter bannerAdapter;
    Context context;

    //Search View
    EditText edtSeachHome;
    ArrayList<AllProductModel> allProductModelList;
    RecyclerView rcvSearchHome;
    AllProductAdapter allProductAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Banner
        rcvBanner = view.findViewById(R.id.rcvBanner);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rcvBanner.setLayoutManager(layoutManager);
        rcvBanner.setHasFixedSize(true);

        //Search View
        edtSeachHome = view.findViewById(R.id.edtSeachHome);
        rcvSearchHome = view.findViewById(R.id.rcvSearchHome);
        rcvSearchHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvSearchHome.setAdapter(allProductAdapter);
        rcvSearchHome.setHasFixedSize(true);

        edtSeachHome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()){
                    allProductModelList.clear();
                    allProductAdapter.notifyDataSetChanged();
                } else {
                    searchProduct(s.toString());
                }
            }
        });

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //ArrayList
        bannersList = new ArrayList<>();

        //Get Data Method
        GetDataFromFirebase();


        //linkView
        imvBest = view.findViewById(R.id.imvBest);
        imvSeat = view.findViewById(R.id.imvSeat);
        imvDesk = view.findViewById(R.id.imvDesk);
        imvSofa = view.findViewById(R.id.imvSofa);
        imvBed = view.findViewById(R.id.imvBed);

        imvNoti = view.findViewById(R.id.imvNotifications);

        frame_collection = view.findViewById(R.id.frame_collection);

        rcvBanner = view.findViewById(R.id.rcvBanner);

        btnShowroom = view.findViewById(R.id.btnShowroom);
        btn3D = view.findViewById(R.id.btn3D);

        //Click event
        imvBest.setOnClickListener(myClick);
        imvSeat.setOnClickListener(myClick);
        imvDesk.setOnClickListener(myClick);
        imvSofa.setOnClickListener(myClick);
        imvBed.setOnClickListener(myClick);

        //Nhúng mặc định fragment
        getActivity().getSupportFragmentManager().beginTransaction( ).replace(R.id.frame_collection, new BestCollectionFragment()).commit();

        imvNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotiActivity.class);
                startActivity(intent);
            }
        });

        btnShowroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewFeatureActivity.class);
                HomeFragment.this.startActivity(intent);
            }
        });
        btn3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewFeatureActivity.class);
                HomeFragment.this.startActivity(intent);
            }
        });

        return view;
    }

    private void searchProduct(String prType) {
        if (!prType.isEmpty()){
            Query firebaseSearchQuery  = databaseReference.child("AllProduct").orderByChild("name").startAt(edtSeachHome.getFreezesText()).endAt(edtSeachHome.getFreezesText() + "\uf8ff");
            firebaseSearchQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    allProductModelList = new ArrayList<>();
                    for(DataSnapshot snapshot : datasnapshot.getChildren()){
                        AllProductModel p =new AllProductModel();
                        p.setPrImage(snapshot.child("prImage").getValue().toString());
                        p.setPrName(snapshot.child("prName").getValue().toString());
                        p.setPrPrice(Double.valueOf(snapshot.child("prPrice").getValue().toString()));
                        p.setPrRvNumber(snapshot.child("prRvNumber").getValue().toString());
                        p.setPrDescription(snapshot.child("prDescription").getValue().toString());
                        p.setPrRating(Float.valueOf(snapshot.child("prRating").getValue().toString()));

                        allProductModelList.add(p);

                    }
                    allProductAdapter = new AllProductAdapter(getContext(),R.layout.item_product_collection,allProductModelList);
                    rcvSearchHome.setAdapter(allProductAdapter);
                    allProductAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void GetDataFromFirebase() {
        Query query = databaseReference.child("Banner");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    Banner banner = new Banner();

                    banner.setImageUrl(snapshot.child("bnImage").getValue().toString());

                    bannersList.add(banner);

                }
                bannerAdapter = new BannerAdapter(getContext(),bannersList);
                rcvBanner.setAdapter(bannerAdapter);
                bannerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if (bannersList != null){
            bannersList.clear();
            if (rcvBanner != null){
                bannerAdapter.notifyDataSetChanged();
            }
        }
        bannersList = new ArrayList<>();
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = null;
            if (view.getId() == R.id.imvBest){
                fragment = new BestCollectionFragment();

            }else if (view.getId() == R.id.imvSeat){
                fragment = new SeatCollectionFragment();

            }else if (view.getId() == R.id.imvDesk){
                fragment = new DestCollectionFragment();

            }else if (view.getId() == R.id.imvSofa){
                fragment = new SofaCollectionFragment();

            }else if (view.getId() == R.id.imvBed){
                fragment = new BedCollectionFragment();

            }
            if (fragment != null){
                fragmentTransaction.replace(R.id.frame_collection, fragment);
                fragmentTransaction.commit();
            }
        }
    };


}