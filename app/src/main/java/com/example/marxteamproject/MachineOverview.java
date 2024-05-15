package com.example.marxteamproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MachineOverview extends AppCompatActivity {

    ImageButton addButton;
    public static String modelNum;
    TextView tractorNote;
    Button Homebutton;
    Button ReloadButton;
    Map<String, Object> currentTractors = new HashMap<>();
    ArrayList<String> tractorList = new ArrayList<>();
    String tractorModelNum;
    int i = 0;
    FireBaseStorage TractorImage1 = new FireBaseStorage();
    int e = 0;


    // TextView tractorName;
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tractor_display_page);
        tractorNote = findViewById(R.id.tractor_note);
        Homebutton = findViewById(R.id.home_button);
        ReloadButton = findViewById(R.id.reload);

        addButton = findViewById(R.id.add_tractor_ImageButton);
        // image = findViewById(R.id.Tractor_image1);

        currentTractors = addUserTractor.getTractorNum();
        if (!(currentTractors == null)) {
            if (currentTractors.isEmpty()) {
                if (tractorNote != null) {
                    tractorNote.setVisibility(View.VISIBLE);
                    ReloadButton.setVisibility(View.VISIBLE);
                }
            } else {


                for (int a = 0; a < Objects.requireNonNull(currentTractors).size(); a++) {
                    tractorList.add((Objects.requireNonNull(currentTractors.get(String.valueOf(a)))).toString());
                }
                android.util.Log.d("Final", tractorList.toString());
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
                        tractorModelNum = tractorList.get(i);
                        tractorModelNum = tractorModelNum.replace("{", "").replace("}", "").replace("[", "").replace("]", "");
                        images[e].setVisibility(View.VISIBLE);

                        TractorImage1.setFirebaseImage(tractorModelNum);
                        TractorImage1.getFirebaseImage((images[e]), this);
                        e++;
                        i++;
                    }

//make sure tractor image isnt already assigned
                    //   Log.d("Current", tractors.getTractorNum().toString());


                }


                if (images[0].getVisibility() == View.VISIBLE) {
                    images[0].setOnClickListener(v -> {
                        modelNum = tractorList.get(0);
                        Intent intent = new Intent(MachineOverview.this, TractorScreen.class);
                        startActivity(intent);
                    });
                }
                if (images[1].getVisibility() == View.VISIBLE) {
                    images[1].setOnClickListener(v -> {
                        modelNum = tractorList.get(1);
                        Intent intent = new Intent(MachineOverview.this, TractorScreen.class);
                        startActivity(intent);
                    });
                }
                if (images[2].getVisibility() == View.VISIBLE) {
                    images[2].setOnClickListener(v -> {
                        modelNum = tractorList.get(2);
                        Intent intent = new Intent(MachineOverview.this, TractorScreen.class);
                        startActivity(intent);
                    });
                }
            }
            addButton.setOnClickListener(v -> {
                Intent intent = new Intent(MachineOverview.this, AddTractor.class);
                startActivity(intent);
            });
            Homebutton.setOnClickListener(v -> {
                Intent intent = new Intent(MachineOverview.this, HomeActivity.class);
                startActivity(intent);
            });
            if (ReloadButton.getVisibility() == View.VISIBLE) {
                ReloadButton.setOnClickListener(v -> {
                    Intent intent = new Intent(MachineOverview.this, LoadingScreen.class);
                    startActivity(intent);
                });
            }
        }


    }
}