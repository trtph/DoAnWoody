package com.example.woodygroupapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.searchAdapter;
import com.example.model.searchProduct;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    ImageView imvSearch;
    RecyclerView rcvCategory, rcvRooms, rcvRecently;
    searchAdapter adapter;
    ArrayList<searchProduct> searchProducts, productRooms, productRecently;

    TextView txtView1, txtView2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        rcvRecently=view.findViewById(R.id.rcvRecently);
        rcvRooms=view.findViewById(R.id.rcvRooms);
        rcvCategory=view.findViewById(R.id.rcvCategory);

        txtView1=view.findViewById(R.id.txtView1);
        txtView2=view.findViewById(R.id.txtView2);

        imvSearch = view.findViewById(R.id.imvSearch);
        imvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager manager3 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);


        rcvCategory.setLayoutManager(manager1);
        rcvRooms.setLayoutManager(manager2);
        rcvRecently.setLayoutManager(manager3);


        Drawable drawable1 = ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.custom_divider);
        Drawable drawable2 = ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.custom_divider);
        Drawable drawable3 = ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.custom_divider);


        searchProducts = new ArrayList<searchProduct>();
        searchProducts.add(new searchProduct(R.drawable.h1, "Furniture"));
        searchProducts.add(new searchProduct(R.drawable.h2, "Kitchen & appliances"));
        searchProducts.add(new searchProduct(R.drawable.h3, "Beds & mattresses"));

        productRooms = new ArrayList<searchProduct>();
        productRooms.add(new searchProduct(R.drawable.r1, "Living room"));
        productRooms.add(new searchProduct(R.drawable.r2, "Dining room"));
        productRooms.add(new searchProduct(R.drawable.r3, "Bathroom"));

        productRecently = new ArrayList<searchProduct>();
        productRecently.add(new searchProduct(R.drawable.img, "Chair"));


        adapter = new searchAdapter(getContext(), searchProducts);
        rcvCategory.setAdapter(adapter);


        adapter = new searchAdapter(getContext(), productRooms);
        rcvRooms.setAdapter(adapter);


        adapter = new searchAdapter(getContext(), productRecently);
        rcvRecently.setAdapter(adapter);

        txtView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(),ListAllCategory.class);
                startActivity(intent);
            }
        });

        txtView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(),ListAllRooms.class);
                startActivity(intent);
            }
        });

        return view;


    }
}

