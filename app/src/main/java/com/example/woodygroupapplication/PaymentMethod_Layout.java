package com.example.woodygroupapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PaymentMethod_Layout extends AppCompatActivity {

    EditText editText7, edtCard;
    Button btnConfirmPayment;

    ConstraintLayout CashOnDelivery;

    String[] credit_card = {"Agribank", "ACB", "BIDV","Techcombank", "Vietcombank", "Sacombank","ViettinBank"};
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapter;

    ImageView btnBackPayment;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_layout);

        editText7=findViewById(R.id.editText7);
        edtCard=findViewById(R.id.edtCard);

        CashOnDelivery = findViewById(R.id.CashOnDelivery);

        btnConfirmPayment=findViewById(R.id.btnConfirmPayment);

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
        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout_Layout.txtPayment.setText(edtCard.getText());
                startActivity(new Intent(PaymentMethod_Layout.this, Checkout_Layout.class));
            }
        });

    }
}