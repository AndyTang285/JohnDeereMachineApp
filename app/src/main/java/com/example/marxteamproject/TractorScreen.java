package com.example.marxteamproject;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TractorScreen extends AppCompatActivity {

   public ImageButton button;
   public String tractortype;
   public String ModelNum;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.tractor_screen);
        FireBaseStorage test = new FireBaseStorage();
        super.onCreate(savedInstanceState);
       test.mymethod(findViewById(R.id.Tractor_image_1), this);
       Database test2 = new Database();
       tractortype = "Combines";
       ModelNum = "S650 (2014)";
       Database.Tractor(tractortype, ModelNum);

       /* button = findViewById(R.id.image_back_button);


        button.setOnClickListener(view -> {
            Intent intent = new Intent(TractorScreen.this, MainActivity.class);
            startActivity(intent);
        });

        */
    }
    }
