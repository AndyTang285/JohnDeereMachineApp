package com.example.marxteamproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TractorScreen extends AppCompatActivity {


   public TextView TractorTextView;
   public String tractortype;
   public String ModelNum;

   public String InfoType;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.tractor_screen);


        FireBaseStorage TractorImage = new FireBaseStorage();
        super.onCreate(savedInstanceState);
       TractorImage.FirebaseImage(findViewById(R.id.Tractor_image_1), this);
       TractorTextView = findViewById(R.id.status_info_text);
       tractortype = "Row Crop Tractors";
       ModelNum = "8R 250 (2021)";
       InfoType = "Specs";
       DatabaseInfo.Tractor(tractortype, ModelNum, TractorTextView, InfoType );
        InfoType = "Info";
        TractorTextView = findViewById(R.id.info_info_text);
        DatabaseInfo.Tractor(tractortype, ModelNum, TractorTextView, InfoType );
       /* button = findViewById(R.id.image_back_button);


        button.setOnClickListener(view -> {
            Intent intent = new Intent(TractorScreen.this, MainActivity.class);
            startActivity(intent);
        });

        */
    }
    }
