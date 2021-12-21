package com.example.woodygroupapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.model.SaleProduct;

public class ReviewBannerActivity extends AppCompatActivity {

    ImageView imvThumb, imvBack;
    TextView txtName, txtRvNumber;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_banner);
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

        SaleProduct s = (SaleProduct) bundle.get("object_sale");
        Glide.with(ReviewBannerActivity.this).load(s.getPrImage()).into(imvThumb);
        txtName.setText(s.getPrName());
        txtRvNumber.setText("" + s.getPrRvNumber()+" reviews");
        ratingBar.setRating(s.getPrRating());

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                finish();
            }
        });
    }
}