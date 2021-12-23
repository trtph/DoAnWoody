package com.example.woodygroupapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {

    MaterialButton btnSend;
    ImageView imvBack;
    EditText edtEmail;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        btnSend = findViewById(R.id.btnSend);
        imvBack = findViewById(R.id.imvBack);
        edtEmail = findViewById(R.id.edtEmailForgotPassword);
        progressDialog = new ProgressDialog(this);
    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = edtEmail.getText().toString().trim();
                if (emailAddress.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPasswordActivity.this,"Link sent ! Please check your email",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(ForgotPasswordActivity.this,"Please enter correct Email",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
//                ForgotPass();
//                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            }
        });
    }

//    private void ForgotPass() {
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        String emailAddress = edtEmail.getText().toString();
//
//        if (emailAddress.isEmpty()){
//            Toast.makeText(ForgotPasswordActivity.this,"Please enter your Email",Toast.LENGTH_SHORT).show();
//        }
//        else{
//            auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(ForgotPasswordActivity.this,"Link sent ! Please check your email",Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(ForgotPasswordActivity.this,"Please enter correct Email",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
}