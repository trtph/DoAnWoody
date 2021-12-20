package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.utils.AppUtils;
import com.example.utils.NameUtils;

public class Checkout_Layout extends AppCompatActivity {

    ImageView btnChangeAdd, btnBackCheckout, btnChangePayment;
    TextView textView7,txtAddress,txtNameAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_layout);

        textView7=findViewById(R.id.textView7);
        txtAddress=findViewById(R.id.txtAddress);
        txtNameAdd=findViewById(R.id.txtNameAdd);

        btnChangeAdd = findViewById(R.id.btnChangeAdd);
        btnChangePayment = findViewById(R.id.btnChangePayment);
        btnBackCheckout = findViewById(R.id.btnBackCheckout);

        if(getIntent().getExtras() !=null) {
            AppUtils appUtils = (AppUtils) getIntent().getExtras().get("object_card");
            textView7.setText(appUtils.getCard());
        }

        if(getIntent().getExtras() !=null) {
            NameUtils nameUtils = (NameUtils) getIntent().getExtras().get("object_name");
            txtNameAdd.setText(nameUtils.getName());
        }


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
                startActivity(new Intent(Checkout_Layout.this, MainActivity.class));
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new ShoppingCartFragment()).commit();
            }
        });

    }
}