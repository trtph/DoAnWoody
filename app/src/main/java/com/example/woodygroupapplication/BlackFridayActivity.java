package com.example.woodygroupapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.Adapter.ProductCollectionAdapter;
import com.example.Adapter.SaleProdutcApdater;
import com.example.model.ProductCollection;
import com.example.model.SaleProduct;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BlackFridayActivity extends AppCompatActivity {

    RecyclerView rcvBlackFriday;
    ImageView imvShopCart, imvBack;

    ArrayList<SaleProduct> saleProducts;
    SaleProdutcApdater adapter;
    Context context;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_friday);
        linkViews();
        addEvents();

        LinearLayoutManager manager = new LinearLayoutManager(getApplication(),LinearLayoutManager.VERTICAL, false);
        rcvBlackFriday.setLayoutManager(manager);

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
                outRect.top = mSpace;
                outRect.bottom = mSpace;
            }
        }

        rcvBlackFriday.addItemDecoration(new SpacesItemDecortion(10, 10));
    }

    private void linkViews() {
        rcvBlackFriday = findViewById(R.id.rcvBlackFriday);
        imvShopCart = findViewById(R.id.imvShopCart);
        imvBack = findViewById(R.id.imvBack);
    }

    private void addEvents() {
        imvShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open Shoping Cart
            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Back to Home
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetDataFromFirebase() {
        Query query = databaseReference.child("BlackFriday");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                saleProducts = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    SaleProduct s =new SaleProduct();
                    s.setPrImage(snapshot.child("prImage").getValue().toString());
                    s.setPrName(snapshot.child("prName").getValue().toString());
                    s.setPrPrice(Double.valueOf(snapshot.child("prPrice").getValue().toString()));
                    s.setPrPriceSale(Double.valueOf(snapshot.child("prPriceSale").getValue().toString()));
                    s.setPrRvNumber(snapshot.child("prRvNumber").getValue().toString());
                    s.setPrDescription(snapshot.child("prDescription").getValue().toString());
                    s.setPrRating(Float.valueOf(snapshot.child("prRating").getValue().toString()));

                    saleProducts.add(s);
                }
                adapter = new SaleProdutcApdater(getApplication(), R.layout.item_sale_product, saleProducts);
                rcvBlackFriday.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}