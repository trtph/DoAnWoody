package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddShipping_Layout extends AppCompatActivity {

    ImageView btnBackAddShip;
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipping_layout);

        btnBackAddShip = findViewById(R.id.btnBackAddShip);
        switchCompat = findViewById(R.id.swichcompat);

        btnBackAddShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddShipping_Layout.this, Checkout_Layout.class));
                finish();
            }
        });
    }
}