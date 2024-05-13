package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CreatePinActivity extends AppCompatActivity {

    MaterialButton createPinBtn;
    MaterialButton cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_pin_screen);

        createPinBtn = findViewById(R.id.create_pin_btn);
        cancelBtn = findViewById(R.id.cancel_pin_btn);

        createPinBtn.setOnClickListener((v)-> savePinToFirebase());
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreatePinActivity.this, MapActivity.class);
                intent.putExtra("PinCanceled", true);
                startActivity(intent);
            }
        });

    }

    private void savePinToFirebase() {
    }


}
