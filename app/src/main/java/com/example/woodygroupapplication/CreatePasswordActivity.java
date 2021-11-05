package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class CreatePasswordActivity extends AppCompatActivity {
    MaterialButton btnCreatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        btnCreatePassword = findViewById(R.id.btnCreatePassword);
    }

    private void addEvents() {
        btnCreatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreatePasswordActivity.this, LoginActivity.class));
                Toast.makeText(CreatePasswordActivity.this, "Password create successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}