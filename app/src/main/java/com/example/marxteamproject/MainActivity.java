package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText newEmailText;
    EditText  newPasswordText;
    Button loginBtn;
    ProgressBar progressBar;
    TextView createAccountBtnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        newEmailText = findViewById(R.id.email_edit_text);
        newPasswordText = findViewById(R.id.password_edit_text);
        loginBtn = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progress_bar);
        createAccountBtnTextView = findViewById(R.id.create_account_text_view_btn);

        loginBtn.setOnClickListener((v)-> loginUser());
        createAccountBtnTextView.setOnClickListener((v)->startActivity(new Intent(MainActivity.this, CreateAccountActivity.class)));
    }

    public void loginUser() {
        String createEmail = newEmailText.getText().toString();
        String createPassword = newPasswordText.getText().toString();

        boolean isValidated = validateData(createEmail, createPassword);
        if (!isValidated) {
            return;
        }

        loginAccountInFirebase(createEmail, createPassword);
    }

    public void changeInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email, String password) {
        //validate the data that are input by the user

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            newEmailText.setError("Email is invalid!");
            return false;
        }

        if(password.length()<6) {
            newPasswordText.setError("Password length is invalid");
        }

        return true;
    }

    void loginAccountInFirebase(String email, String password) {
        changeInProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if (task.isSuccessful()) {
                    //login is success
                    if(firebaseAuth.getCurrentUser().isEmailVerified()) {
                        //go to home screen
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    } else {
                        Utility.showToast(MainActivity.this, "Email not verified, Please verify your email.");
                    }
                } else {
                    //login failed
                    Utility.showToast(MainActivity.this, task.getException().getLocalizedMessage());
                }
            }
        });
    }
}