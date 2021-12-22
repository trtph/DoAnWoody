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
import com.example.model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SofaCollectionFragment extends Fragment {

    RecyclerView rcvSofa;

    ArrayList<ProductModel> product;
    ProductCollectionAdapter adapter;
    Context context;

    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sofa_collection, container, false);
        rcvSofa = view.findViewById(R.id.rcvSofa);

        //Set orientation
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rcvSofa.setLayoutManager(manager);

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Get Data Method
        GetDataFromFirebase();

        //Set space between items
        class SpacesItemDecoration extends RecyclerView.ItemDecoration{
            private final int mSpace;
            public SpacesItemDecoration(int mSpace){
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
        rcvSofa.addItemDecoration(new SpacesItemDecoration(30));

        return view;
    }

    private void GetDataFromFirebase() {
        Query query = databaseReference.child("AllProduct").child("Product").orderByChild("prType").equalTo("sofa");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                product = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProductModel p =new ProductModel();
                    p.setPrImage(snapshot.child("prImage").getValue().toString());
                    p.setPrName(snapshot.child("prName").getValue().toString());
                    p.setPrPrice(Double.valueOf(snapshot.child("prPrice").getValue().toString()));
                    p.setPrRvNumber(snapshot.child("prRvNumber").getValue().toString());
                    p.setPrDescription(snapshot.child("prDescription").getValue().toString());
                    p.setPrRating(Float.valueOf(snapshot.child("prRating").getValue().toString()));

                    product.add(p);
                }
                adapter = new ProductCollectionAdapter(getActivity(), R.layout.item_product_collection, product, new IClickItemCollection() {
                    @Override
                    public void onClickItemCollection(ProductModel p) {
                        onClickToDetail(p);
                    }
                });
                rcvSofa.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Go to Product Detail
    private void onClickToDetail(ProductModel p){
        Intent intent = new Intent(getContext(), ProductDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", p);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }
}