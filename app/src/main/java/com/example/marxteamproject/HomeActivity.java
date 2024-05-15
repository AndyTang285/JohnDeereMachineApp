package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    LinearLayout mapBtn;
    LinearLayout notesBtn;
    LinearLayout dealerBtn;
    LinearLayout weatherBtn;

    TextView categoriesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        mapBtn = findViewById(R.id.homeMap);
        notesBtn = findViewById(R.id.homeNotes);
        dealerBtn = findViewById(R.id.homeDealer);
        weatherBtn = findViewById(R.id.homeWeather);
        categoriesBtn = findViewById(R.id.textViewMachines);

        mapBtn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, MapActivity.class);
            startActivity(intent);
        });

        notesBtn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, NotesActivity.class);
            startActivity(intent);
        });

        dealerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, DealerActivity.class);
            startActivity(intent);
        });
        weatherBtn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, WeatherActivity.class);
            startActivity(intent);
        });

        categoriesBtn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, LoadingScreen.class);
            startActivity(intent);
        });


    }
}



