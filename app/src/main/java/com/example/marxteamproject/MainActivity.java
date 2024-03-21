package com.example.marxteamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tractor_screen);
//References
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Tractors = database.getReference("Tractors");
        DatabaseReference Tractor1032E = Tractors.child("1032E");
        DatabaseReference Tractor1032EMaintenance = Tractor1032E.child("Maintenance");
        DatabaseReference Tractors1032EMaintenanceFuel = Tractor1032EMaintenance.child("Fuel");
// Read from the database
        Tractors1032EMaintenanceFuel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("FireBase", "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("FireBase", "Failed to read value.", error.toException());
            }
        });
    }
}