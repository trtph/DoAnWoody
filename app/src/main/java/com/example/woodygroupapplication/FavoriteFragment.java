package com.example.woodygroupapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.FavouriteAdapter;
import com.example.model.FavouriteProduct;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {
    RecyclerView rcvFavouriteProduct;
    FavouriteAdapter adapter;
    ArrayList<FavouriteProduct> favouriteProducts;

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rcvFavouriteProduct = view.findViewById(R.id.rcvFavouriteProduct);



        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvFavouriteProduct.setLayoutManager(manager);

        DividerItemDecoration decoration = new DividerItemDecoration(rcvFavouriteProduct.getContext(),manager.getOrientation());
        rcvFavouriteProduct.addItemDecoration(decoration);


        favouriteProducts =new ArrayList<FavouriteProduct>();
        adapter = new FavouriteAdapter(getContext(),favouriteProducts);
        rcvFavouriteProduct.setAdapter(adapter);

        //Insert data
        db.collection("AddToWishlist").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        FavouriteProduct cartModel1 = documentSnapshot.toObject(FavouriteProduct.class);
                        favouriteProducts.add(cartModel1);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });

        return view;
    }
}