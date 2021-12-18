package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.model.ProductCollection;

public class DetailActivity extends AppCompatActivity {
    ImageView imvThumb, add_quantity, remove_quantity;
    TextView txtName, txtPrice, txtRvNumber, txtDes, txtQuantity;
    RatingBar ratingBar;
    int totalQuantity = 1;

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

    }
}