package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {
    TextView txtBackLogin;
    MaterialButton btnContinueRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        linkViews();
        addEvent();
    }

    private void linkViews() {
        txtBackLogin = findViewById(R.id.txtBackLogin);
        btnContinueRegister = findViewById(R.id.btnContinueRegister);
    }

    private void addEvent() {
        txtBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btnContinueRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, VerificationActivity.class));
            }
        });
    }
}