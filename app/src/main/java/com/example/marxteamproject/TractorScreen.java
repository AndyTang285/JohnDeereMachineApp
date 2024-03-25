package com.example.marxteamproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TractorScreen extends MainActivity{

   public ImageButton button;
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.tractor_screen);
        super.onCreate(savedInstanceState);
        button = findViewById(R.id.image_back_button);
    }



}