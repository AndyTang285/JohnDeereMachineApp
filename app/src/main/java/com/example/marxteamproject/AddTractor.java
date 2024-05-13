package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddTractor extends AppCompatActivity {

    public static addUserTractor tractor = new addUserTractor();
    Button Sbutton;

    EditText TractorNum;
    EditText TractorType; 
    public static String TractorModelNum;
    public static String MachineType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tractor_add_forum);
        addUserTractor tractor = new addUserTractor();
        Sbutton = findViewById(R.id.submit_button);
        TractorNum = findViewById(R.id.Tractor_name);
        TractorType = findViewById(R.id.Tractor_type);

        Sbutton.setOnClickListener(
                view -> {

                    MachineType = TractorType.getText().toString();
                    TractorModelNum = TractorNum.getText().toString();
                    tractor.saveNoteToFirebase(TractorModelNum , "work", MachineType);
                    Log.w("current" , tractor.getTractorNum().toString());
                    Intent intent = new Intent(AddTractor.this, TractorScreen.class);
                    startActivity(intent);

                });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, TractorModelNumbers);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                TractorNum;
        textView.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, TractorTypes);
        AutoCompleteTextView textView2 = (AutoCompleteTextView)
                TractorType;
        textView2.setAdapter(adapter2);


        }


    private static final String[] TractorModelNumbers = new String[] {
//Combines
            "S650 (2014)",
            "S690 (2021)",
            "S760 (2023)",
            "T670 (2021)",
            "X9 1100 (2023)",
//Drapers
            "HDF45 (2022)",
            "HDF50 (2023)",
            "HDR45 (2023)",
            "HDR50 (2023)",
            "RDF45 (2021)",
//Hay and Forage
            "450E (2023)",
//Planters
            "1725C (2023)",
            "1775NT (2023)",
            "BD60 (2022)",
            "DB44 (2021)",
            "DR12 (2022)",
//Row Crop Tractors
            "8R 230 (2022)",
            "8R 250 (2021)",
            "8R 310 (2022)",
            "8RT 370 (2023)",
            "8RX 410 (2021)",
//Speciality Equipment
            "5075EN (Built)",
            "5075GV (Built)",
            "5105MH (Built)",
            "5130ML (Built)",
            "6120EH (Built)",
//Tilling Disks
            "2633VT (2022)",
            "2660VT (2020)",
            "2680HP (2021)",
//Utility Tractors
            "5067E (2024)",
            "5105M (2011)",
            "6130M (2023)",
            "6R 130 (2023)",
            "6R 250 (2022)"

    };
    private static final String[] TractorTypes = new String[]{

            "Combines",
            "Drapers",
            "Front Loaders",
            "Hay and Forage",
            "Planters",
            "Row Crop Tractors",
            "Specialty Equipment",
            "Tilling Disks",
            "Utility Tractors"


    };

}


