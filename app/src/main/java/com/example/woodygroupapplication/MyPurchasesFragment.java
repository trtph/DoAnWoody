package com.example.woodygroupapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.FavouriteAdapter;
import com.example.model.FavouriteProduct;

import java.util.ArrayList;

public class MyPurchasesFragment extends Fragment {

    RecyclerView rcvMyPurchases;
    FavouriteAdapter adapter;
    ArrayList<FavouriteProduct> favouriteProducts;
    FrameLayout frame_collection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rcvMyPurchases = view.findViewById(R.id.rcvFavouriteProduct);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvMyPurchases.setLayoutManager(manager);

        DividerItemDecoration decoration = new DividerItemDecoration(rcvMyPurchases.getContext(),manager.getOrientation());
        rcvMyPurchases.addItemDecoration(decoration);
        favouriteProducts =new ArrayList<FavouriteProduct>();
        favouriteProducts.add(new FavouriteProduct(R.drawable.woolrug,"WOOL RUG",19000));
        favouriteProducts.add(new FavouriteProduct(R.drawable.juterug,"JUTE RUG",14000));
        favouriteProducts.add(new FavouriteProduct(R.drawable.acacia,"ACACIA",15000));

        adapter = new FavouriteAdapter(getContext(),favouriteProducts);
        rcvMyPurchases.setAdapter(adapter);

//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
//        rcvMyPurchases = view.findViewById(R.id.rcvFavouriteProduct);
//
//        //Nhúng mặc định fragment
//        getActivity().getSupportFragmentManager().beginTransaction( ).replace(R.id.frame_collection, new com.example.woodygroupapplication.BestCollectionFragment()).commit();
//
//        frame_collection = view.findViewById(R.id.frame_collection);

        return view;
    }
}

