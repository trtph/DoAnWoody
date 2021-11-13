package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Checkout_Layout extends AppCompatActivity {

    ImageView btnChangeAdd, btnBackCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_layout);

        btnChangeAdd = findViewById(R.id.btnChangeAdd);

        btnChangeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Checkout_Layout.this, AddShipping_Layout.class));
            }
        });

    }
}