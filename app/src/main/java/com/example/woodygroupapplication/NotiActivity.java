package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NotiActivity extends AppCompatActivity {

    ImageView imvBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        imvBackHome = findViewById(R.id.imvBackHome);
    }

    private void addEvents() {
        imvBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotiActivity.this, MainActivity.class));
            }
        });
    }
}