package com.example.woodygroupapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.model.ProductCollection;

public class DetailActivity extends AppCompatActivity {
    ImageView imvThumb;
    TextView txtName, txtPrice, txtRvNumber, txtDes;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        linkViews();

        addEvents();
    }

    private void addEvents() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        ProductCollection p = (ProductCollection) bundle.get("object");

        Glide.with(DetailActivity.this).load(p.getPrImage()).into(imvThumb);
        txtName.setText(p.getPrName());
        txtPrice.setText("$ " + p.getPrPrice());
        txtRvNumber.setText("(" + p.getPrRvNumber()+" reviews)");
        txtDes.setText(p.getPrDescription());
        ratingBar.setRating(p.getPrRating());
    }

    private void linkViews() {
        imvThumb = findViewById(R.id.imvThumb);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtRvNumber = findViewById(R.id.txtRvNumber);
        txtDes = findViewById(R.id.txtDescription);
        ratingBar = findViewById(R.id.ratingBar);
    }
}