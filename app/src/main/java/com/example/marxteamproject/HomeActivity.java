package com.example.marxteamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

        dealerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DealerActivity.class);
                startActivity(intent);
            }
        });
        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

        categoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, TractorScreen.class);
                startActivity(intent);

            }
        });
    }
}