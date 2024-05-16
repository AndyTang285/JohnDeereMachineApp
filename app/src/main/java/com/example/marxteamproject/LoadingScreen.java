package com.example.marxteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class LoadingScreen extends AppCompatActivity {
    static Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        map = addUserTractor.getTractorNum();
        // Delay for 1 second before starting the MachineOverview activity.
        new Handler().postDelayed(() -> {
            Log.d("something" , map.toString());
            // Create an intent to start the MachineOverview activity.
            Intent intent = new Intent(LoadingScreen.this, MachineOverview.class);

            // Start the MachineOverview activity.

                startActivity(intent);


            // Finish the LoadingScreen activity.
            finish();
        }, 3500);
    }
}