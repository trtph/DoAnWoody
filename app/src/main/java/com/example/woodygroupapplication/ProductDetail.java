package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.model.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ProductDetail extends AppCompatActivity {
    ImageView imvThumb, add_quantity, remove_quantity, imvBack, imvAddToFavorite ;
    TextView txtName, txtPrice, txtPriceSale, txtRvNumber, txtDes, txtQuantity;
    RatingBar ratingBar;
    Button btnAddToCart;

    ProductModel p;
    int totalQuantity = 1;
    int totalPrice = 0;

    FirebaseFirestore firestore; //Cloud Firestore
    FirebaseAuth auth; //Authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        linkViews();
        addEvents();

        firestore = FirebaseFirestore.getInstance(); //Cloud Firestore
        auth = FirebaseAuth.getInstance(); //Authentication
    }
    private void linkViews() {
        imvBack = findViewById(R.id.imvBack);
        imvAddToFavorite = findViewById(R.id.imvAddToFavorite);
        imvThumb = findViewById(R.id.imvThumb);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtPriceSale = findViewById(R.id.txtPriceSale);
        txtRvNumber = findViewById(R.id.txtRvNumber);
        txtDes = findViewById(R.id.txtDescription);
        ratingBar = findViewById(R.id.ratingBar);

        add_quantity = findViewById(R.id.add_quantity);
        remove_quantity = findViewById(R.id.remove_quantity);
        txtQuantity = findViewById(R.id.txtQuantity);
        btnAddToCart = findViewById(R.id.btnAddToCart);
    }

    private void addEvents() {
        //Get object to display up to the Detail
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        p = (ProductModel) bundle.get("object");
        Glide.with(ProductDetail.this).load(p.getPrImage()).into(imvThumb);
        txtName.setText(p.getPrName());
        txtPrice.setText("$ " + p.getPrPrice());
        if (p.getPrPriceSale() != null){
            txtPriceSale.setText("$ "+p.getPrPriceSale());
        }
        txtRvNumber.setText("(" + p.getPrRvNumber()+" reviews)");
        txtDes.setText(p.getPrDescription());
        ratingBar.setRating(p.getPrRating());
        totalPrice = (int) (p.getPrPrice() * totalQuantity);

        //Set OnClick
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                finish();
            }
        });

        add_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 9){
                    totalQuantity++;
                    txtQuantity.setText(String.valueOf(totalQuantity));
                    totalPrice = (int) (p.getPrPrice() * totalQuantity);
                }
            }
        });

        remove_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity >= 2){
                    totalQuantity--;
                    txtQuantity.setText(String.valueOf(totalQuantity));
                    totalPrice = (int) (p.getPrPrice() * totalQuantity);
                }
            }
        });

        txtRvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail.this, Review.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", p);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddedToCart();
            }
        });
        imvAddToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddedToWishlist();
            }
        });
    }

    private void AddedToWishlist() {
        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("prThumb", p.getPrImage());
        cartMap.put("prName", txtName.getText().toString());
        cartMap.put("prPrice", txtPrice.getText().toString());
        cartMap.put("totalQuantity", txtQuantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            firestore.collection("AddToWishlist").document(auth.getCurrentUser().getUid())
                    .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(ProductDetail.this, "Added " + txtName.getText().toString() + " To Your Wishlist", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
            firestore.collection("AddToWishlist").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(ProductDetail.this, "Added " + txtName.getText().toString() + " To Your Wishlist", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

    private void AddedToCart() {

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("prThumb", p.getPrImage());
        cartMap.put("prName", txtName.getText().toString());
        cartMap.put("prPrice", txtPrice.getText().toString());
        cartMap.put("totalQuantity", txtQuantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                    .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(ProductDetail.this, "Added " + txtQuantity.getText().toString() + " " + txtName.getText().toString() + " To Your Cart", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }else {
            firestore.collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(ProductDetail.this, "Added " + txtQuantity.getText().toString() + " " + txtName.getText().toString() + " To Your Cart", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }



    }


}