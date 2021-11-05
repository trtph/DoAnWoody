package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class VerificationActivity extends AppCompatActivity {
    MaterialButton btnSendCode;
    TextView txtSendAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        btnSendCode = findViewById(R.id.btnSendCode);
        txtSendAgain = findViewById(R.id.txtSendAgain);
    }

    private void addEvents() {
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerificationActivity.this, CreatePasswordActivity.class));
            }
        });
        txtSendAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VerificationActivity.this, "Verification code has been sent again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}