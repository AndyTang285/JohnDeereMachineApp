package com.example.marxteamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {

    EditText newEmailText;
    EditText  newPasswordText;
    EditText confirmPasswordText;
    Button createAccountBtn;
    ProgressBar progressBar;
    TextView loginBtnTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        newEmailText = findViewById(R.id.email_edit_text);
        newPasswordText = findViewById(R.id.password_edit_text);
        confirmPasswordText = findViewById(R.id.confirm_password_edit_text);
        createAccountBtn = findViewById(R.id.create_account_btn);
        progressBar = findViewById(R.id.progress_bar);
        loginBtnTextView = findViewById(R.id.login_text_view_btn);

        createAccountBtn.setOnClickListener(v-> createAccount() );
        loginBtnTextView.setOnClickListener(v-> finish());
    }

    public void createAccount() {
        String createEmail = newEmailText.getText().toString();
        String createPassword = newPasswordText.getText().toString();
        String confirmPassword = confirmPasswordText.getText().toString();

        boolean isValidated = validateData(createEmail, createPassword, confirmPassword);
        if (!isValidated) {
            return;
        }

        createAccountinFirebase(createEmail, createPassword);
    }

    public void createAccountinFirebase (String email, String password) {
        changeInProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if (task.isSuccessful()) {
                    //create account is done
                    Toast.makeText(CreateAccountActivity.this,"Successfully create account, Check email to verify", Toast.LENGTH_SHORT).show();
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    firebaseAuth.signOut();
                    finish();
                } else {
                    //failure
                    Toast.makeText(CreateAccountActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void changeInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            createAccountBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            createAccountBtn.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email, String password, String confirmPassword) {
        //validate the data that are input by the user

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            newEmailText.setError("Email is invalid!");
            return false;
        }


        if(password.length()<6) {
            newPasswordText.setError("Password length is invalid");
        }

        if(!password.equals(confirmPassword)) {
            confirmPasswordText.setError("Password not matched");
            return false;
        }

        return true;
    }
}