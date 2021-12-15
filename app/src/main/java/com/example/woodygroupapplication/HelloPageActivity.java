package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class HelloPageActivity extends AppCompatActivity {
    MaterialButton btnShopNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_page);
        linkViews();
        addEvent();
    }

    private void linkViews() {
        btnShopNow = findViewById(R.id.btnShopNow);
    }

    private void addEvent() {
        btnShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelloPageActivity.this, MainActivity.class));
            }
        });
    }
}