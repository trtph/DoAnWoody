package com.example.woodygroupapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.ProductAdapter2;
import com.example.model.Product;

import java.util.ArrayList;


public class BedCollectionFragment extends Fragment {


    RecyclerView rcvBedCollection;
    ProductAdapter2 productAdapter2;
    ArrayList<Product> products;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bed_collection, container, false);
        rcvBedCollection = view.findViewById(R.id.rcvBedCollection);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rcvBedCollection.setLayoutManager(manager);

        DividerItemDecoration decoration = new DividerItemDecoration(rcvBedCollection.getContext(), manager.getOrientation());

        rcvBedCollection.addItemDecoration(decoration);


        products = new ArrayList<Product>();
        products.add(new Product(R.drawable.langfjaell, "LÅNGFJÄLL", 129));
        products.add(new Product(R.drawable.loberget, "LOBERGET/BLYSKÄR", 24.99));
        products.add(new Product(R.drawable.utespelare, "UTESPELARE", 99.95));
        products.add(new Product(R.drawable.valfed, "VALFRED / SIBBEN", 29.99));

        productAdapter2 = new ProductAdapter2(getContext(), products);
        rcvBedCollection.setAdapter(productAdapter2);
        return view;
    }
}