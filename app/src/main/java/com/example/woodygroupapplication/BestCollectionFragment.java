package com.example.woodygroupapplication;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.ProductApdater;
import com.example.model.Product;

import java.util.ArrayList;


public class BestCollectionFragment extends Fragment {

    RecyclerView rcvBestCollection;
    ProductApdater apdater;
    ArrayList<Product> products;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_best_collection, container, false);
        rcvBestCollection = view.findViewById(R.id.rcvBestCollection);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rcvBestCollection.setLayoutManager(manager);

//        DividerItemDecoration decoration = new DividerItemDecoration(rcvBestCollection.getContext(), manager.getOrientation());
//        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divider);
//        decoration.setDrawable(drawable);
//        rcvBestCollection.addItemDecoration(decoration);



        products = new ArrayList<Product>();
        products.add(new Product(R.drawable.baby, "Baby & children", 20));
        products.add(new Product(R.drawable.decoration, "Decoration", 200));
        products.add(new Product(R.drawable.bathroom, "Bathroom products", 56));
        products.add(new Product(R.drawable.lingting, "LingTing", 120));

        apdater = new ProductApdater(getContext(), products);
        rcvBestCollection.setAdapter(apdater);

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

        rcvBestCollection.addItemDecoration(new SpacesItemDecortion(50, 30));

        return view;
    }



}