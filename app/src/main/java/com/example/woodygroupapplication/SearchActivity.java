package com.example.woodygroupapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.Adapter.AllProductAdapter;
import com.example.Adapter.ProductCollectionAdapter;
import com.example.Adapter.SaleProdutcApdater;
import com.example.model.AllProductModel;
import com.example.model.ProductCollection;
import com.example.model.SaleProduct;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    RecyclerView rcvProduct;
    ImageView imvBack;
    EditText edtSearch;

    ArrayList<AllProductModel> allProductModels;
    AllProductAdapter adapter;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        linkViews();
        addEvents();

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Lấy data từ Firebase về cho rcv
        GetDataFromFirebase();

    }

    private void addEvents() {
        //Set cột, hướng cho RecyclerView
        GridLayoutManager manager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL, false);
        rcvProduct.setLayoutManager(manager);

        //Khoảng cách giữa các item trong RecyclerView
        class SpacesItemDecortion extends RecyclerView.ItemDecoration{
            private final int mSpace, space;
            public SpacesItemDecortion(int space, int mSpace){
                this.mSpace = mSpace;
                this.space = space;
            }
            @Override
            public void getItemOffsets(Rect outRect,
                                       View view,
                                       RecyclerView parent, RecyclerView.State state){
                outRect.top = mSpace;
                outRect.bottom = mSpace;

                outRect.left = space;
            }
        }
        rcvProduct.addItemDecoration(new SpacesItemDecortion(20, 30));

        //Back OnClick
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                finish();
            }
        });

        //Search View
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void linkViews() {
        rcvProduct = findViewById(R.id.rcvProduct);
        imvBack =findViewById(R.id.imvBack);
        edtSearch = findViewById(R.id.edtSearch);
    }

    //Lấy data Firebase
    private void GetDataFromFirebase() {
        Query query = databaseReference.child("AllProduct");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allProductModels = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    AllProductModel all =new AllProductModel();
                    all.setPrImage(snapshot.child("prImage").getValue().toString());
                    all.setPrName(snapshot.child("prName").getValue().toString());
                    all.setPrPrice(Double.valueOf(snapshot.child("prPrice").getValue().toString()));
                    all.setPrRvNumber(snapshot.child("prRvNumber").getValue().toString());
                    all.setPrDescription(snapshot.child("prDescription").getValue().toString());
                    all.setPrRating(Float.valueOf(snapshot.child("prRating").getValue().toString()));

                    allProductModels.add(all);
                }
                adapter = new AllProductAdapter(getBaseContext(), R.layout.item_product, allProductModels);
                rcvProduct.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Search View
    private void filter(String name){
        ArrayList<AllProductModel> filteredList = new ArrayList<>();

        for (AllProductModel all : allProductModels){
            if (all.getPrName().toLowerCase().contains(name.toLowerCase())){
                filteredList.add(all);
            }
        }
        adapter.filterList(filteredList);
    }
}