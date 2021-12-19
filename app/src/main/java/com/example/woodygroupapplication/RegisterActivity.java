package com.example.woodygroupapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {



    TextView txtBackLogin;
    EditText edtEmail,edtPass,edtConfirmPass;
    MaterialButton btnContinueRegister;

    ProgressDialog progressDialog;

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
        edtConfirmPass = findViewById(R.id.edtConfirmPass);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass =findViewById(R.id.edtPass);

        progressDialog = new ProgressDialog(this);
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
                Register();
            }
        });
    }

    private void Register() {
        String strPass = edtPass.getText().toString().trim();
        String strConfirmPass = edtConfirmPass.getText().toString().trim();
        String strEmail = edtEmail.getText().toString().trim();

        //CheckEmailAndPassword
        if (strPass.equals(strConfirmPass) )
        {//Successful
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }else {
            Toast.makeText(RegisterActivity.this, "The password is not the same", Toast.LENGTH_SHORT).show();
        }

        //Register
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(strEmail,strPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Register success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }else{
                    Toast.makeText(getApplicationContext(), "Register failed! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}