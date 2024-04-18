package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TractorSetupScreen  extends AppCompatActivity {
    ImageButton addButton;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tractor_setup_screen);
       addButton = findViewById(R.id.add_tractor_button);

        addButton.setOnClickListener(view -> {

            Intent intent = new Intent(TractorSetupScreen.this, AddTractor.class);
            startActivity(intent);

        });


    }

}
