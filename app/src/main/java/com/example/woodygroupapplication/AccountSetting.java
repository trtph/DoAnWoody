package com.example.woodygroupapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AccountSetting extends AppCompatActivity {

    ImageView imvEditName, imvEditPass;
    TextView txtName, txtPass;
    Button btnLogOut,btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        
        linkView();
        addEvent();
    }

    private void linkView() {
        imvEditPass = findViewById(R.id.imvEditPass);
        imvEditName = findViewById(R.id.imvEditName);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnSave = findViewById(R.id.btnSave);
        txtName = findViewById(R.id.txtNameSetting);
        txtPass = findViewById(R.id.txtPassSetting);
    }

    private void addEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
