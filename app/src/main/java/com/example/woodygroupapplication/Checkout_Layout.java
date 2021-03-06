package com.example.woodygroupapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.utils.AddUtils;
import com.example.utils.NameUtils;
import com.google.android.material.button.MaterialButton;

public class Checkout_Layout extends AppCompatActivity {

    ImageView btnChangeAdd, btnBackCheckout, btnChangePayment;

    TextView textView7,txtAddress,txtNameAdd;
    static TextView txtPayment;

    TextView txtOrder, txtDelivery, txtTotalCK;

    MaterialButton btnSubmitOrder;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_layout);

        textView7=findViewById(R.id.txtPayment);
        txtAddress=findViewById(R.id.txtAddress);
        txtNameAdd=findViewById(R.id.txtNameAdd);

        btnChangeAdd = findViewById(R.id.btnChangeAdd);
        btnChangePayment = findViewById(R.id.btnChangePayment);
        btnBackCheckout = findViewById(R.id.imvBackCheckout);
        btnSubmitOrder = findViewById(R.id.btnSubmitOrder);
        txtOrder = findViewById(R.id.txtOrder);
        txtDelivery = findViewById(R.id.txtDelivery);
        txtTotalCK = findViewById(R.id.txtTotalCK);
        txtPayment = findViewById(R.id.txtPayment);

        progressDialog = new ProgressDialog(this);

        //Caculate Cart
        String order = ShoppingCartFragment.txtToTal.getText().toString();
        String delivery = txtDelivery.getText().toString().substring(2);
        Double total = (Double.parseDouble(order.substring(2)) + Double.parseDouble(delivery));
        txtOrder.setText(order);
        txtTotalCK.setText("$ " + total);

        if(getIntent().getExtras() !=null) {

            NameUtils nameUtils = (NameUtils) getIntent().getExtras().get("object_name");
            txtNameAdd.setText(nameUtils.getName());

            AddUtils addUtils = (AddUtils) getIntent().getExtras().get("object_address");
            txtAddress.setText(addUtils.getAddress());

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
                getSupportFragmentManager().popBackStack();
                finish();
            }
        });
        btnSubmitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Intent intent = new Intent(Checkout_Layout.this, OrderSuccessActivity.class);
                startActivity(intent);
            }
        });

    }

}