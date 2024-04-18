package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.concurrent.atomic.AtomicInteger;

public class MachineOverview extends AppCompatActivity {
    ImageButton addButton;
    LinearLayout linearLayout;

    TextView tractorName;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tractor_display_page);
        addButton = findViewById(R.id.add_tractor_ImageButton);
        linearLayout = findViewById(R.id.LinearLayoutDisplay);
        AtomicInteger count = new AtomicInteger(1);
        //tractorName = findViewById(R.id.Tractor1Name);
       // tractorName.setText(AddTractor.TractorModelNum);
        FireBaseStorage TractorImage1 = new FireBaseStorage();
        TractorImage1.setFirebaseImage(AddTractor.TractorModelNum);
        TractorImage1.getFirebaseImage(findViewById(R.id.Tractor_image1), this);





        addButton.setOnClickListener(
                view -> {
                    findViewById(R.id.Tractor_image2).setVisibility(View.VISIBLE);


        Intent intent = new Intent(MachineOverview.this, AddTractor.class);
                    startActivity(intent);
                });
                }
    }
