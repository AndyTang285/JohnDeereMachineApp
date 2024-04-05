package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button loginBtn;
    EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        loginBtn = findViewById(R.id.Login_btn);

        emailEditText = findViewById(R.id.username_input);
        passwordEditText = findViewById(R.id.password_input);

        loginBtn.setOnClickListener((v)-> loginAccountInFirebase(emailEditText.getText().toString(), passwordEditText.getText().toString()));
    }

    void loginAccountInFirebase(String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }
}

