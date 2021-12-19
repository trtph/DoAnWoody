package com.example.woodygroupapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ImageView imvBackUserAccount;
    TextView edtEmailLogin,edtPassLogin;
    TextView txtForgotPassword, txtSignUp;
    MaterialButton btnSignIn;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtSignUp = findViewById(R.id.txtSignUp);
        imvBackUserAccount = findViewById(R.id.imvBackUserAccount);
        btnSignIn = findViewById(R.id.btnSignIn);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPassLogin);
        progressDialog = new ProgressDialog(this);
    }

    private void addEvents() {
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private void Login() {
        String strPass = edtPassLogin.getText().toString().trim();
        String strEmail = edtEmailLogin.getText().toString().trim();

        if(TextUtils.isEmpty(strEmail)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(strPass)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        //Code để đăng nhập
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(strEmail,strPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong password or account", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}