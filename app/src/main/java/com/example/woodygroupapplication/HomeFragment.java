package com.example.woodygroupapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.BannerAdapter;
import com.example.model.Banner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    ImageView imvBest, imvSeat, imvDesk, imvSofa, imvBed, imvNoti;
    FrameLayout frame_collection;
    RecyclerView rcvBanner;

    //Firebase
    DatabaseReference databaseReference;

    //Variable
    ArrayList<Banner> bannersList;
    BannerAdapter bannerAdapter;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Banner
        rcvBanner = view.findViewById(R.id.rcvBanner);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rcvBanner.setLayoutManager(layoutManager);
        rcvBanner.setHasFixedSize(true);

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


        imvBest.setOnClickListener(myClick);
        imvSeat.setOnClickListener(myClick);
        imvDesk.setOnClickListener(myClick);
        imvSofa.setOnClickListener(myClick);
        imvBed.setOnClickListener(myClick);

        //addEvents
        getActivity().getSupportFragmentManager().beginTransaction( ).replace(R.id.frame_collection, new com.example.woodygroupapplication.BestCollectionFragment()).commit();

        return view;
    }

    private void GetDataFromFirebase() {
        Query query = databaseReference.child("Banner");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    Banner banner = new Banner();

//                    banner.setImageUrl(snapshot.child("bannerImage").getValue().toString());

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
                fragment = new com.example.woodygroupapplication.BestCollectionFragment();

            }else if (view.getId() == R.id.imvSeat){
                fragment = new com.example.woodygroupapplication.SeatCollectionFragment();

            }else if (view.getId() == R.id.imvDesk){
                fragment = new com.example.woodygroupapplication.DestCollectionFragment();

            }else if (view.getId() == R.id.imvSofa){
                fragment = new SofaCollectionFragment();

            }else if (view.getId() == R.id.imvBed){
                fragment = new com.example.woodygroupapplication.BedCollectionFragment();

            }
            if (fragment != null){
                fragmentTransaction.replace(R.id.frame_collection, fragment);
                fragmentTransaction.commit();
            }
//            if(view.getId()==R.id.imvNotifications){
//                startActivity(new Intent(getContext(),NotiActivity.class));
//            }

        }
    };


}