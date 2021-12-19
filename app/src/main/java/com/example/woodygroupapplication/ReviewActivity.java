package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.model.ProductCollection;

public class ReviewActivity extends AppCompatActivity {

    ImageView imvThumb;
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
        txtName = findViewById(R.id.txtName);
        txtRvNumber = findViewById(R.id.txtRvNumber);
        ratingBar = findViewById(R.id.ratingBar);
    }

    private void addEvents() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        ProductCollection p = (ProductCollection) bundle.get("object");

        Glide.with(ReviewActivity.this).load(p.getPrImage()).into(imvThumb);
        txtName.setText(p.getPrName());
        txtRvNumber.setText("" + p.getPrRvNumber()+" reviews");
        ratingBar.setRating(p.getPrRating());
    }
}