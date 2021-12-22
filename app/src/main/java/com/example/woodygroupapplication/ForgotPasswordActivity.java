package com.example.woodygroupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    MaterialButton btnSend;

    EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        btnSend = findViewById(R.id.btnSend);
        edtEmail = findViewById(R.id.edtEmailForgotPassword);
    }

    private void addEvents() {
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ForgotPasswordActivity.this, VerificationActivity.class));
//            }
//        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPass();
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            }
        });
    }

    private void ForgotPass() {

        String emailAddress = edtEmail.getText().toString();
//        String emailAddress = "vuvt19411c@st.uel.edu.vn";

        if (edtEmail == null ){
            Toast.makeText(ForgotPasswordActivity.this,"Please enter your Email",Toast.LENGTH_SHORT).show();
        }
        else{
            FirebaseAuth auth = FirebaseAuth.getInstance();

            auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPasswordActivity.this,"Link sent ! Please check your email",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ForgotPasswordActivity.this,"Please enter correct Email",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}