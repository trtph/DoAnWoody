package com.example.woodygroupapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.BannerAdapter;
import com.example.model.BannerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    ImageView imvBest, imvSeat, imvDesk, imvSofa, imvBed, imvNoti;
    Button btnShowroom, btn3D;
    FrameLayout frame_collection;
    RecyclerView rcvBanner;

    //Firebase
    DatabaseReference databaseReference;

    //Variable
    ArrayList<BannerModel> bannersList;
    BannerAdapter bannerAdapter;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);




        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //ArrayList
        bannersList = new ArrayList<>();

        //Get Data Method
        GetDataFromFirebase();

        //linkView
        rcvBanner = view.findViewById(R.id.rcvBanner);
        frame_collection = view.findViewById(R.id.frame_collection);

        imvBest = view.findViewById(R.id.imvBest);
        imvSeat = view.findViewById(R.id.imvSeat);
        imvDesk = view.findViewById(R.id.imvDesk);
        imvSofa = view.findViewById(R.id.imvSofa);
        imvBed = view.findViewById(R.id.imvBed);

        imvNoti = view.findViewById(R.id.imvNotifications);

        btnShowroom = view.findViewById(R.id.btnShowroom);
        btn3D = view.findViewById(R.id.btn3D);

        //Set orientation for banner
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rcvBanner.setLayoutManager(layoutManager);
        rcvBanner.setHasFixedSize(true);

        //Default returns HomeFragment
        getActivity().getSupportFragmentManager().beginTransaction( ).replace(R.id.frame_collection, new BestCollectionFragment()).commit();

        //OnClick event
        imvBest.setOnClickListener(myClick);
        imvSeat.setOnClickListener(myClick);
        imvDesk.setOnClickListener(myClick);
        imvSofa.setOnClickListener(myClick);
        imvBed.setOnClickListener(myClick);

        //Set OnClick
        imvNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Notification.class);
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
        //Onclick end

        return view;
    }

    //Get data
    private void GetDataFromFirebase() {
        Query query = databaseReference.child("Banner");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    BannerModel bannerModel = new BannerModel();

                    bannerModel.setImageUrl(snapshot.child("bnImage").getValue().toString());

                    bannersList.add(bannerModel);

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

    //OnClick Collection
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
                fragment = new DeskCollectionFragment();

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