package com.example.woodygroupapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.SaleProductAdapter;
import com.example.MyInterfaces.IClickItemCollection;
import com.example.model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiscountEvent extends AppCompatActivity {

    RecyclerView rcvBlackFriday;
    ImageView imvShopCart, imvBack;

    ArrayList<ProductModel> product;
    SaleProductAdapter adapter;
    Context context;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discount_event);
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
        imvBack = findViewById(R.id.imvBack);
    }

    private void addEvents() {

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
        Query query = databaseReference.child("AllProduct").child("BlackFriday");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                product = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProductModel p =new ProductModel();
                    p.setPrImage(snapshot.child("prImage").getValue().toString());
                    p.setPrName(snapshot.child("prName").getValue().toString());
                    p.setPrPrice(Double.valueOf(snapshot.child("prPrice").getValue().toString()));
                    p.setPrPriceSale(Double.valueOf(snapshot.child("prPriceSale").getValue().toString()));
                    p.setPrRvNumber(snapshot.child("prRvNumber").getValue().toString());
                    p.setPrDescription(snapshot.child("prDescription").getValue().toString());
                    p.setPrRating(Float.valueOf(snapshot.child("prRating").getValue().toString()));

                    product.add(p);
                }
                adapter = new SaleProductAdapter(getApplication(), R.layout.item_product_discount, product, new IClickItemCollection() {
                    @Override
                    public void onClickItemCollection(ProductModel p) {
                        onClickToDetailProductSale(p);
                    }
                });
                rcvBlackFriday.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
        private void onClickToDetailProductSale(ProductModel p) {
        Intent intent = new Intent(this, ProductDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", p);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}