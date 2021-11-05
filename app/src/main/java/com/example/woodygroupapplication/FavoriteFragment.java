package com.example.woodygroupapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.FavouriteAdapter;
import com.example.Adapter.ShoppingBagAdapter;
import com.example.model.FavouriteProduct;
import com.example.model.productshopModel;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {
    RecyclerView rcvFavouriteProduct;
    FavouriteAdapter adapter;
    ArrayList<FavouriteProduct> favouriteProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rcvFavouriteProduct = view.findViewById(R.id.rcvFavouriteProduct);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvFavouriteProduct.setLayoutManager(manager);

        DividerItemDecoration decoration = new DividerItemDecoration(rcvFavouriteProduct.getContext(),manager.getOrientation());
        rcvFavouriteProduct.addItemDecoration(decoration);
        favouriteProducts =new ArrayList<FavouriteProduct>();
        favouriteProducts.add(new FavouriteProduct(R.drawable.woolrug,"WOOL RUG",19000));
        favouriteProducts.add(new FavouriteProduct(R.drawable.juterug,"JUTE RUG",14000));
        favouriteProducts.add(new FavouriteProduct(R.drawable.acacia,"ACACIA",15000));



        adapter = new FavouriteAdapter(getContext(),favouriteProducts);
        rcvFavouriteProduct.setAdapter(adapter);
        return view;
    }
}