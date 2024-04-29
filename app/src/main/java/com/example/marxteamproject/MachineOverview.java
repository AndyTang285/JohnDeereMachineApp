package com.example.marxteamproject;

import android.annotation.SuppressLint;
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
    @SuppressLint("UseCompatLoadingForDrawables")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i = 0;
        int e = 0;
        String tractorModelNum;
        setContentView(R.layout.tractor_display_page);
        addButton = findViewById(R.id.add_tractor_ImageButton);
       // image = findViewById(R.id.Tractor_image1);
        FireBaseStorage TractorImage1 = new FireBaseStorage();
        currentTractors = addUserTractor.getTractorNum();
         ImageView[] images = {
          findViewById(R.id.Tractor_image1),
          findViewById(R.id.Tractor_image2),
          findViewById(R.id.Tractor_image3)
        };

        while(currentTractors.get(String.valueOf(i)) != null && e <= 2){

//Tractor 1 Image load
            tractorModelNum = (String) currentTractors.get(String.valueOf(i));
            tractorModelNum = tractorModelNum.replace("{", "").replace("}","");
                //image.setImageResource(images[e]);
            TractorImage1.setFirebaseImage(tractorModelNum);
                TractorImage1.getFirebaseImage((images[e]), this);
                e++;
            i++;
        }
//make sure tractor image isnt already assigned
     //   Log.d("Current", tractors.getTractorNum().toString());






        addButton.setOnClickListener(
                view -> {
                  //  findViewById(R.id.Tractor_image2).setVisibility(View.VISIBLE);


        Intent intent = new Intent(MachineOverview.this, AddTractor.class);
                    startActivity(intent);
                });
                }
    }

