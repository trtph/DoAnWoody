package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.FavouriteAdapter;
import com.example.model.FavouriteProduct;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {
    static RecyclerView rcvFavouriteProduct;
    FavouriteAdapter adapter;
    ArrayList<FavouriteProduct> favouriteProducts;

    static ConstraintLayout constraintEmptyWish;

    MaterialButton btnDiscoverWish;
    static ProgressBar progressbar;

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rcvFavouriteProduct = view.findViewById(R.id.rcvFavouriteProduct);
        constraintEmptyWish = view.findViewById(R.id.constraintEmptyWish);
        btnDiscoverWish = view.findViewById(R.id.btnDiscoverWish);
        progressbar = view.findViewById(R.id.progressbarWishlist);
        progressbar.setIndeterminateDrawable(new CubeGrid());
        //Load Data
        progressbar.setVisibility(View.VISIBLE);

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            db.collection("AddToWishlist").document(auth.getCurrentUser().getUid())
                    .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                            String documentID = documentSnapshot.getId();
                            FavouriteProduct cartModel1 = documentSnapshot.toObject(FavouriteProduct.class);
                            cartModel1.setDocumentID(documentID);
                            favouriteProducts.add(cartModel1);
                            adapter.notifyDataSetChanged();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                    //Switch constraintLayout
                    SwitchLayoutWish(favouriteProducts);

                }
            });
        }else {
            db.collection("AddToWishlist").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                            String documentID = documentSnapshot.getId();
                            FavouriteProduct cartModel1 = documentSnapshot.toObject(FavouriteProduct.class);
                            cartModel1.setDocumentID(documentID);
                            favouriteProducts.add(cartModel1);
                            adapter.notifyDataSetChanged();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                    //Switch constraintLayout
                    SwitchLayoutWish(favouriteProducts);

                }
            });
        }

        //Open View List (Search Activity)
        btnDiscoverWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductList.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public static void SwitchLayoutWish(ArrayList<FavouriteProduct> favouriteProducts) {
        if (favouriteProducts.isEmpty()){
            constraintEmptyWish.setVisibility(View.VISIBLE);
            rcvFavouriteProduct.setVisibility(View.GONE);
            progressbar.setVisibility(View.GONE);
        }else if (!favouriteProducts.isEmpty()){
            constraintEmptyWish.setVisibility(View.GONE);
            rcvFavouriteProduct.setVisibility(View.VISIBLE);
        }
    }
}