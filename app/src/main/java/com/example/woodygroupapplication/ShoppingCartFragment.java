package com.example.woodygroupapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.ShoppingBagAdapter;
import com.example.model.productshopModel;

import java.util.ArrayList;


public class ShoppingCartFragment extends Fragment {

    RecyclerView rcvProduct;
    ShoppingBagAdapter adapter;
    ArrayList<productshopModel> productshopModels;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        rcvProduct=view.findViewById(R.id.rcvProduct);

        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvProduct.setLayoutManager(manager);

        DividerItemDecoration decoration = new DividerItemDecoration(rcvProduct.getContext(),manager.getOrientation());
        rcvProduct.addItemDecoration(decoration);

        productshopModels=new ArrayList<productshopModel>();
        productshopModels.add(new productshopModel(R.drawable.woolrug,"WOOL RUG",19000));
        productshopModels.add(new productshopModel(R.drawable.juterug,"JUTE RUG",14000));
        productshopModels.add(new productshopModel(R.drawable.acacia,"ACACIA",15000));


        adapter = new ShoppingBagAdapter(getContext(),productshopModels);
        rcvProduct.setAdapter(adapter);

        return view;
    }
}
