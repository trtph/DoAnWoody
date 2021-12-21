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

public class ReviewProductActivity extends AppCompatActivity {
    ImageView imvThumb, imvBack;
    TextView txtName, txtRvNumber;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_product);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        imvThumb = findViewById(R.id.imvThumb);
        imvBack = findViewById(R.id.imvBack);
        txtName = findViewById(R.id.txtName);
        txtRvNumber = findViewById(R.id.txtRvNumber);
        ratingBar = findViewById(R.id.ratingBar);
    }

    private void addEvents() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        AllProductModel allProductModel = (AllProductModel) bundle.get("object_product");

        Glide.with(ReviewProductActivity.this).load(allProductModel.getPrImage()).into(imvThumb);
        txtName.setText(allProductModel.getPrName());
        txtRvNumber.setText("( " + allProductModel.getPrRvNumber()+" reviews)");
        ratingBar.setRating(allProductModel.getPrRating());

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                finish();
            }
        });
    }
}