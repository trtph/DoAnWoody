package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ChangePasswordActivity extends AppCompatActivity {
    MaterialButton btnCreatePassword;
    EditText edtPassCreate ,edtConfirmPassCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        btnCreatePassword = findViewById(R.id.btnCreatePassword);
        edtPassCreate = findViewById(R.id.edtPassCreate);
        edtConfirmPassCreate = findViewById(R.id.edtConfirmPasswordCreate);
    }

    private void addEvents() {
        btnCreatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPass = edtPassCreate.getText().toString().trim();
                String strConfirmPass = edtConfirmPassCreate.getText().toString().trim();


            }
        });
    }
}