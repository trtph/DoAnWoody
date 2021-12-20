package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.model.ProductCollection;
import com.example.model.SaleProduct;

public class ReviewActivity extends AppCompatActivity {

    ImageView imvThumb, imvBack;
    TextView txtName, txtRvNumber;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
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
        //Product Collection
        ProductCollection p = (ProductCollection) bundle.get("object");

        Glide.with(ReviewActivity.this).load(p.getPrImage()).into(imvThumb);
        txtName.setText(p.getPrName());
        txtRvNumber.setText("" + p.getPrRvNumber()+" reviews");
        ratingBar.setRating(p.getPrRating());

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                finish();
            }
        });

        //Product Sale
//        SaleProduct s = (SaleProduct) bundle.get("object_sale");
//        Glide.with(ReviewActivity.this).load(s.getPrImage()).into(imvThumb);
//        txtName.setText(s.getPrName());
//        txtRvNumber.setText("" + s.getPrRvNumber()+" reviews");
//        ratingBar.setRating(s.getPrRating());

    }
}