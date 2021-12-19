package com.example.woodygroupapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.ProductCollectionAdapter;
import com.example.MyInterfaces.IClickItemCollection;
import com.example.model.ProductCollection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BedCollectionFragment extends Fragment {


    RecyclerView rcvBed;

    ArrayList<ProductCollection> productCollections;
    ProductCollectionAdapter adapter;
    Context context;

    DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bed_collection, container, false);
        rcvBed = view.findViewById(R.id.rcvBed);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rcvBed.setLayoutManager(manager);

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Get Data Method
        GetDataFromFirebase();

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

        rcvBed.addItemDecoration(new SpacesItemDecortion(30, 30));

        return view;
    }

    private void GetDataFromFirebase() {
        Query query = databaseReference.child("ProductCollection").child("BedCollection");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productCollections = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProductCollection p =new ProductCollection();
                    p.setPrImage(snapshot.child("prImage").getValue().toString());
                    p.setPrName(snapshot.child("prName").getValue().toString());
                    p.setPrPrice(Double.valueOf(snapshot.child("prPrice").getValue().toString()));
                    p.setPrRvNumber(snapshot.child("prRvNumber").getValue().toString());
                    p.setPrDescription(snapshot.child("prDescription").getValue().toString());
                    p.setPrRating(Float.valueOf(snapshot.child("prRating").getValue().toString()));

                    productCollections.add(p);
                }
                adapter = new ProductCollectionAdapter(getActivity(), R.layout.item_product_collection, productCollections);
                rcvBed.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void onClickToDetail(ProductCollection p){
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", p);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null){
            adapter.release();
        }
    }
}