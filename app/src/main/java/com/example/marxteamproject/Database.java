package com.example.marxteamproject;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Database extends MainActivity {
    public static void Tractor(String TractorType, String ModelNum) {
//References
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Combines = database.getReference(TractorType);
        DatabaseReference CombineS650 = Combines.child(ModelNum);
        DatabaseReference CombineS650Specs = CombineS650.child("Specs");
        DatabaseReference CombineS650EngineHours = CombineS650Specs.child("Engine Hours");

      // Read from the database
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String axle = ds.child("Axle").getValue(String.class);
                    String SeparatorHours = ds.child("Separator Hours").getValue(String.class);
                    String EngineHr = ds.child("Engine Hours").getValue(String.class);
                    String newsImageUrl = ds.child("newsImageUrl").getValue(String.class);
                    String newsTitle = ds.child("newsTitle").getValue(String.class);
                    String newsUrl = ds.child("newsUrl").getValue(String.class);
                    Log.d("TAG", axle + " / " + SeparatorHours + " / " + EngineHr + " / " + newsImageUrl + " / " + newsTitle + " / " + newsUrl);
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("FireBase", "Failed to read value.", error.toException());
            }
        };
        CombineS650Specs.addListenerForSingleValueEvent(eventListener);
    }
}
