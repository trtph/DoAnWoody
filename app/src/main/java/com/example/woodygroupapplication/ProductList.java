package com.example.woodygroupapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.Adapter.ProductAdapter;
import com.example.MyInterfaces.IClickItemCollection;
import com.example.model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    RecyclerView rcvProduct;
    ImageView imvBack;
    EditText edtSearch;

    ArrayList<ProductModel> product;
    ProductAdapter adapter;

    DatabaseReference databaseReference; //Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        linkViews();
        addEvents();

        //Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        GetDataFromFirebase();

    }

    private void linkViews() {
        rcvProduct = findViewById(R.id.rcvProduct);
        imvBack =findViewById(R.id.imvBack);
        edtSearch = findViewById(R.id.edtSearch);
    }

    private void addEvents() {
        //Set orientation
        GridLayoutManager manager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL, false);
        rcvProduct.setLayoutManager(manager);

        //Set space between items
        class SpacesItemDecoration extends RecyclerView.ItemDecoration{
            private final int mSpace, space;
            public SpacesItemDecoration(int space, int mSpace){
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
        rcvProduct.addItemDecoration(new SpacesItemDecoration(20, 30));

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

    //Get data from Realtime Database
    private void GetDataFromFirebase() {
        Query query = databaseReference.child("AllProduct").child("Product");
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
                adapter = new ProductAdapter(getBaseContext(), R.layout.item_product, product, new IClickItemCollection() {
                    @Override
                    public void onClickItemCollection(ProductModel p) {
                        onClickToDetail(p);
                    }
                });
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
        ArrayList<ProductModel> filteredList = new ArrayList<>();

        for (ProductModel all : product){
            if (all.getPrName().toLowerCase().contains(name.toLowerCase())){
                filteredList.add(all);
            }
        }
        adapter.filterList(filteredList);
    }
    //Get object to Product Detail
    private void onClickToDetail(ProductModel p){
        Intent intent = new Intent(this, ProductDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", p);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}