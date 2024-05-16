package com.example.marxteamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoriesActivity extends AppCompatActivity {

    Button work_c_button;
    Button home_c_button;
    Button back_from_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_screen);
        work_c_button = findViewById(R.id.workCButton);
        home_c_button = findViewById(R.id.homeCButton);
        back_from_categories = findViewById(R.id.backFromCategories);


        work_c_button.setOnClickListener(view -> {
            work_c_button.setTranslationZ(0);
            addUserTractor.setScreen(true);
            Intent intent = new Intent(CategoriesActivity.this, LoadingScreen.class);
            startActivity(intent);
        });

        home_c_button.setOnClickListener(view -> {
            home_c_button.setTranslationZ(0);
            addUserTractor.setScreen(false);
            Intent intent = new Intent(CategoriesActivity.this, LoadingScreen.class);
            startActivity(intent);
        });

        back_from_categories.setOnClickListener(view -> {
            Intent intent = new Intent(CategoriesActivity.this, HomeActivity.class);
            startActivity(intent);
        });

    }
}