package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MachineOverview extends AppCompatActivity {
    ImageButton addButton;
    Map<String, Object> currentTractors = new HashMap<>();
    public ImageView image;
   // TextView tractorName;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tractor_display_page);
        addButton = findViewById(R.id.add_tractor_ImageButton);
        image = findViewById(R.id.Tractor_image1);
        FireBaseStorage TractorImage1 = new FireBaseStorage();
        addUserTractor tractors = new addUserTractor();
        currentTractors = tractors.getTractorNum();
     //   Log.d("Current", tractors.getTractorNum().toString());

        if(image.getDrawable().getConstantState() == this.getResources().getDrawable(R.drawable.placeholder).getConstantState()) {
//Tractor 1 Image load
            TractorImage1.setFirebaseImage(AddTractor.TractorModelNum);
            TractorImage1.getFirebaseImage(findViewById(R.id.Tractor_image1), this);

        }




        addButton.setOnClickListener(
                view -> {
                  //  findViewById(R.id.Tractor_image2).setVisibility(View.VISIBLE);


        Intent intent = new Intent(MachineOverview.this, AddTractor.class);
                    startActivity(intent);
                });
                }
    }

