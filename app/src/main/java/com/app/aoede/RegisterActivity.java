package com.app.aoede;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEmail = findViewById(R.id.txtRegisterEmail);
        txtPassword = findViewById(R.id.txtRegisterPassword);
        btnRegister = findViewById(R.id.btnRegisterComplete);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = txtEmail.getText().toString();
                String passwordInput = txtPassword.getText().toString();

                if (TextUtils.isEmpty(emailInput)|| TextUtils.isEmpty(passwordInput)){
                    Toast.makeText(RegisterActivity.this,"EMPTY CREDENTIALS",Toast.LENGTH_SHORT).show();

                }else if (passwordInput.length() < 6){
                    Toast.makeText(RegisterActivity.this, "PASSWORD TOO SHORT", Toast.LENGTH_SHORT).show();

                }else {
                    registerUser(emailInput, passwordInput);

                }
            }
        });
    }

    private void registerUser(String emailInput, String passwordInput) {
        mAuth.createUserWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "USER SUCCESSFULLY REGISTERED", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,FirebaseLogIn.class));
                }else {
                    Toast.makeText(RegisterActivity.this, "REGISTER FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}