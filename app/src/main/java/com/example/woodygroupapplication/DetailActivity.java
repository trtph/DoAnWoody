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
import com.example.model.ProductCollection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
    ImageView imvThumb, add_quantity, remove_quantity;
    TextView txtName, txtPrice, txtRvNumber, txtDes, txtQuantity, txtIntro;
    RatingBar ratingBar;
    Button btnAddToCart;

    ProductCollection p;
    int totalQuantity = 1;
    int totalPrice = 0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ProductCollection productCollection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        linkViews();

        addEvents();

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    private void addEvents() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        p = (ProductCollection) bundle.get("object");

        Glide.with(DetailActivity.this).load(p.getPrImage()).into(imvThumb);
        txtName.setText(p.getPrName());
        txtPrice.setText("$ " + p.getPrPrice());
        txtRvNumber.setText("(" + p.getPrRvNumber()+" reviews)");
        txtDes.setText(p.getPrDescription());
        ratingBar.setRating(p.getPrRating());
        totalPrice = (int) (p.getPrPrice() * totalQuantity);


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
                Intent intent = new Intent(DetailActivity.this, ReviewActivity.class);
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
    }

    private void AddedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM đ, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("prThumb", p.getPrImage());
        cartMap.put("prName", txtName.getText().toString());
        cartMap.put("prPrice", txtPrice.getText().toString());
//        cartMap.put("currentDate", currentDate);
//        cartMap.put("currentTime", currentTime);
        cartMap.put("totalQuantity", txtQuantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailActivity.this, "Added " + txtQuantity.getText().toString() + " " + txtName.getText().toString() + " To Your Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void linkViews() {
        imvThumb = findViewById(R.id.imvThumb);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtRvNumber = findViewById(R.id.txtRvNumber);
        txtDes = findViewById(R.id.txtDescription);
        ratingBar = findViewById(R.id.ratingBar);

        add_quantity = findViewById(R.id.add_quantity);
        remove_quantity = findViewById(R.id.remove_quantity);
        txtQuantity = findViewById(R.id.txtQuantity);
        btnAddToCart = findViewById(R.id.btnAddToCart);

    }
}