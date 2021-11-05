package com.example.woodygroupapplication;

import android.graphics.Rect;
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

public class SofaCollectionFragment extends Fragment {


    RecyclerView rcvSofaCollection;
    ProductAdapter2 productAdapter2;
    ArrayList<Product> products;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sofa_collection, container, false);
        rcvSofaCollection = view.findViewById(R.id.rcvSofaCollection);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rcvSofaCollection.setLayoutManager(manager);

//        DividerItemDecoration decoration = new DividerItemDecoration(rcvSofaCollection.getContext(), manager.getOrientation());
//        rcvSofaCollection.addItemDecoration(decoration);

        class SpacesItemDecortion extends RecyclerView.ItemDecoration{
            private final int mSpace;
            public SpacesItemDecortion(int space, int mSpace){
                this.mSpace = mSpace;
            }
            @Override
            public void getItemOffsets(Rect outRect,
                                       View view,
                                       RecyclerView parent, RecyclerView.State state){
                outRect.left = mSpace;
                outRect.right = mSpace;
            }
        }

        rcvSofaCollection.addItemDecoration(new SpacesItemDecortion(50, 30));


        products = new ArrayList<Product>();
        products.add(new Product(R.drawable.kivik, "KIVIK TWO SEAT", 349.99));
        products.add(new Product(R.drawable.soederhamn, "SÖDERHAMN", 648.99));
        products.add(new Product(R.drawable.kivik_light, "KIVIK FOUR SEAT", 849.93));
        products.add(new Product(R.drawable.norsborg, "NORSBORG", 394.99));

        productAdapter2 = new ProductAdapter2(getContext(), products);
        rcvSofaCollection.setAdapter(productAdapter2);
        return view;
    }
}