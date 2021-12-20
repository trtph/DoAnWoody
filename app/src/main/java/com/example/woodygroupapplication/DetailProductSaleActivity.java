package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.model.ProductCollection;
import com.example.model.SaleProduct;

public class DetailProductSaleActivity extends AppCompatActivity {
    ImageView imvThumb, add_quantity, remove_quantity, imvBack;
    TextView txtName, txtPrice, txtPriceSale, txtRvNumber, txtDes, txtQuantity;
    RatingBar ratingBar;

    SaleProduct s;
    int totalQuantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product_sale);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        imvThumb = findViewById(R.id.imvThumb1);
        txtName = findViewById(R.id.txtName1);
        txtPrice = findViewById(R.id.txtPrice1);
        txtPriceSale = findViewById(R.id.txtPriceSale1);
        txtRvNumber = findViewById(R.id.txtRvNumber1);
        txtDes = findViewById(R.id.txtDescription1);
        ratingBar = findViewById(R.id.ratingBar1);

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
        s = (SaleProduct) bundle.get("object_sale");

        Glide.with(DetailProductSaleActivity.this).load(s.getPrImage()).into(imvThumb);
        txtName.setText(s.getPrName());
        txtPrice.setText("$ " + s.getPrPrice());
        txtPriceSale.setText("$ " + s.getPrPriceSale());
        txtRvNumber.setText("(" + s.getPrRvNumber()+" reviews)");
        txtDes.setText(s.getPrDescription());
        ratingBar.setRating(s.getPrRating());

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
                Intent intent = new Intent(DetailProductSaleActivity.this, BlackFridayActivity.class);
                startActivity(intent);
            }
        });
        txtRvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailProductSaleActivity.this, ReviewSaleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_sale", s);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}