package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.ShoppingBagAdapter;
import com.example.model.productshopModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


public class ShoppingCartFragment extends Fragment {

    RecyclerView rcvProduct;
    ShoppingBagAdapter adapter;
    ArrayList<productshopModel> productshopModels;
    TextView txtToTal;
    LinearLayout layoutdelete;

    MaterialButton btnCheckout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        View view1 = inflater.inflate(R.layout.shoppingbag_items, container, false);
        rcvProduct=view.findViewById(R.id.rcvProduct);
        btnCheckout= view.findViewById(R.id.btnCheckout);
        txtToTal = view.findViewById(R.id.txtTotal);

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

        //Open Checkout Activity
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Checkout_Layout.class));
            }
        });
        caculateCart();
        return view;
    }

    public Double caculateCart() {
        double total = 0;
        for (int i = 0; i < productshopModels.size(); i++) {
                total = total + (productshopModels.get(i).getProductPrice());
        }
        txtToTal.setText("$ " + total);
        return total;

    }
}
