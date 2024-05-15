package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TractorScreen extends AppCompatActivity {


    public TextView TractorInfoTextView;

   public TextView TractorStatusTextView;
   public String tractortype;
   public String ModelNum;
public static String tractor1Info;
   public String tractor1Specs;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.tractor_screen);
        super.onCreate(savedInstanceState);
        if(MachineOverview.modelNum != null){
            ModelNum = MachineOverview.modelNum;
            MachineOverview.modelNum = null;
        }
        else {
            ModelNum = AddTractor.TractorModelNum;
            AddTractor.TractorModelNum = null;
        }
        FireBaseStorage TractorImage1 = new FireBaseStorage();
        TractorImage1.setFirebaseImage(ModelNum);



       TractorImage1.getFirebaseImage(findViewById(R.id.Tractor_image_1), this);
       TractorStatusTextView = findViewById(R.id.status_info_text);

       tractortype = extra.getType(ModelNum);
       DatabaseInfo Tractor1 = new DatabaseInfo();
        TractorInfoTextView = findViewById(R.id.info_info_text);
        TextView TractorName = findViewById(R.id.tractor_name);
        TractorName.setText(ModelNum);


        tractor1Info = Tractor1.getTractorInfo(tractortype, ModelNum, TractorInfoTextView);
        tractor1Specs = Tractor1.getTractorSpecs(tractortype, ModelNum, TractorStatusTextView);




        Button backButton = findViewById(R.id.button2);


        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(TractorScreen.this, MachineOverview.class);
            startActivity(intent);
        });

            }
    }
