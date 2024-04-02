package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        button = findViewById(R.id.tractorScreenButton);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TractorScreen.class);
            startActivity(intent);
        });


    }

}