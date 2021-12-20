package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Checkout_Layout extends AppCompatActivity {

    ImageView btnChangeAdd, btnBackCheckout, btnChangePayment;
    TextView txtOrder, txtDelivery, txtTotalCK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_layout);

        btnChangeAdd = findViewById(R.id.btnChangeAdd);
        btnChangePayment = findViewById(R.id.btnChangePayment);
        btnBackCheckout = findViewById(R.id.imvBackCheckout);
        txtOrder = findViewById(R.id.txtOrder);
        txtDelivery = findViewById(R.id.txtDelivery);
        txtTotalCK = findViewById(R.id.txtTotalCK);

        //Caculate cart
        String order = ShoppingCartFragment.txtToTal.getText().toString();
        String delivery = txtDelivery.getText().toString().substring(2);
        Double total = (Double.parseDouble(order.substring(2)) + Double.parseDouble(delivery));
        txtOrder.setText(order);
        txtTotalCK.setText("$ " + total);

        btnChangeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Checkout_Layout.this, AddShipping_Layout.class));
            }
        });
        btnChangePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Checkout_Layout.this, PaymentMethod_Layout.class));
            }
        });
        btnBackCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().popBackStack();
                finish();
            }
        });

    }
}