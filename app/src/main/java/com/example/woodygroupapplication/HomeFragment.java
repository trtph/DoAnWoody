package com.example.woodygroupapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class HomeFragment extends Fragment {


    ImageView imvBest, imvSeat, imvDesk, imvSofa, imvBed, imvNoti;
    FrameLayout frame_collection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //linkView
        imvBest = view.findViewById(R.id.imvBest);
        imvSeat = view.findViewById(R.id.imvSeat);
        imvDesk = view.findViewById(R.id.imvDesk);
        imvSofa = view.findViewById(R.id.imvSofa);
        imvBed = view.findViewById(R.id.imvBed);

        imvNoti = view.findViewById(R.id.imvNotifications);

        frame_collection = view.findViewById(R.id.frame_collection);

        imvBest.setOnClickListener(myClick);
        imvSeat.setOnClickListener(myClick);
        imvDesk.setOnClickListener(myClick);
        imvSofa.setOnClickListener(myClick);
        imvBed.setOnClickListener(myClick);

        //addEvents
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_collection, new com.example.woodygroupapplication.BestCollectionFragment()).commit();

//        imvNoti.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), NotiActivity.class));
//            }
//        });
//
        return view;
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