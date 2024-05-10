package com.example.marxteamproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MachineOverview extends AppCompatActivity {
    ImageButton addButton;
    Map<String, Object> currentTractors = new HashMap<>();
    ArrayList<String> tractorList = new ArrayList<>();

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

        for (int a = 0; a < currentTractors.size(); a++) {
            tractorList.add(Objects.requireNonNull(currentTractors.get(String.valueOf(a))).toString());
        }
        Log.d("Final", tractorList.toString());
        //get rid of duplicates
        Set<String> set = new HashSet<>(tractorList);
        tractorList.clear();
        tractorList.addAll(set);
        Log.d("Final", tractorList.toString());
        ImageView[] images = {
                findViewById(R.id.Tractor_image1),
                findViewById(R.id.Tractor_image2),
                findViewById(R.id.Tractor_image3)
        };

        for (int a = 0; a < tractorList.size(); a++) {
            if (e <= 2) {

//Tractor 1 Image load
                tractorModelNum = (String) tractorList.get(i);
                tractorModelNum = tractorModelNum.replace("{", "").replace("}", "").replace("[", "").replace("]", "");
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
}

