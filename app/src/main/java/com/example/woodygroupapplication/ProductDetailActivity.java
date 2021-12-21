package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.model.AllProductModel;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView imvThumb, add_quantity, remove_quantity, imvBack;
    TextView txtName, txtPrice, txtRvNumber, txtDes, txtQuantity;
    RatingBar ratingBar;

    AllProductModel allProduct;
    int totalQuantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        linkViews();
        addEvents();
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

        imvBack = findViewById(R.id.imvBack);
    }

    private void addEvents() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        allProduct = (AllProductModel) bundle.get("object_product");

        Glide.with(ProductDetailActivity.this).load(allProduct.getPrImage()).into(imvThumb);
        txtName.setText(allProduct.getPrName());
        txtPrice.setText("$ " + allProduct.getPrPrice());
        txtRvNumber.setText("(" + allProduct.getPrRvNumber()+" reviews)");
        txtDes.setText(allProduct.getPrDescription());
        ratingBar.setRating(allProduct.getPrRating());

        add_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 9){
                    totalQuantity++;
                    txtQuantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
        remove_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity >= 2){
                    totalQuantity--;
                    txtQuantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        txtRvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, ReviewProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_product", allProduct);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}