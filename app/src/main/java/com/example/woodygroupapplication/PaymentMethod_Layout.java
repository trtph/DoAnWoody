package com.example.woodygroupapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethod_Layout extends AppCompatActivity {

    String[] credit_card = {"Agribank", "Vietcombank", "Sacombank"};
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapter;

    ImageView btnBackPayment;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_layout);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        btnBackPayment = findViewById(R.id.btnBackPayment);

        adapter = new ArrayAdapter<String>(this, R.layout.dropdown_creditcard,credit_card);

        autoCompleteTextView.setAdapter(adapter);

        btnBackPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentMethod_Layout.this, Checkout_Layout.class));
                finish();
            }
        });

    }
}