package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class OrderSuccessActivity extends AppCompatActivity {
    MaterialButton btnBackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);
        linkViews();
        addEvent();
    }

    private void linkViews() {
        btnBackToHome = findViewById(R.id.btnBackToHome);
    }

    private void addEvent() {
        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSuccessActivity.this, MainActivity.class));
                finishAffinity();
            }
        });
    }
}