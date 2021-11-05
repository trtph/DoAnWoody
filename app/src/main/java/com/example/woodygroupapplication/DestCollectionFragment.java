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


public class DestCollectionFragment extends Fragment {
    RecyclerView rcvDeskCollection;
    ProductAdapter2 productAdapter2;
    ArrayList<Product> products;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desk_collection, container, false);
        rcvDeskCollection = view.findViewById(R.id.rcvDeskCollection);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rcvDeskCollection.setLayoutManager(manager);

//        DividerItemDecoration decoration = new DividerItemDecoration(rcvDeskCollection.getContext(), manager.getOrientation());
//        rcvDeskCollection.addItemDecoration(decoration);
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

        rcvDeskCollection.addItemDecoration(new SpacesItemDecortion(50, 30));

        products = new ArrayList<Product>();
        products.add(new Product(R.drawable.lack_white, "LACK", 19.99));
        products.add(new Product(R.drawable.tommaryd_dark, "TOMMARYD DARK", 129.99));
        products.add(new Product(R.drawable.tommaryd_white, "TOMMARYD WHITE", 119.95));
        products.add(new Product(R.drawable.gladom, "GLADOM", 14.99));

        productAdapter2 = new ProductAdapter2(getContext(), products);
        rcvDeskCollection.setAdapter(productAdapter2);
        return view;
    }
}