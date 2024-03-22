package com.example.marxteamproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class TractorScreen extends MainActivity{

    ImageButton button;
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.tractor_screen);
        super.onCreate(savedInstanceState);
        button = findViewById(R.id.image_back_button);
        setClickListener();
    }

    private void setClickListener() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("button", "button was pressed");
                setContentView(R.layout.home_screen);
            }
        });
    }}