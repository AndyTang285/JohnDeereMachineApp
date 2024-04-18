package com.example.marxteamproject;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInfo {
    public String getTractorInfo(String TractorType, String ModelNum, TextView text) {
        List<String> myList = new ArrayList<>();
//References
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Combines = database.getReference(TractorType);
        DatabaseReference CombineS650 = Combines.child(ModelNum);
        DatabaseReference CombineS650Specs = CombineS650.child("Info");
        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myList.add(snapshot.getKey() + ": " + snapshot.getValue() + "\n");
                String List = myList.toString();
                List = List.replace("[", " ").replace("]", "").replace(",", "");
                text.setText(List);
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        CombineS650Specs.addChildEventListener(childEventListener);
         return (text.getText().toString());
    }
    public String getTractorSpecs(String TractorType, String ModelNum, TextView text) {
        List<String> myList = new ArrayList<>();
//References
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Combines = database.getReference(TractorType);
        DatabaseReference CombineS650 = Combines.child(ModelNum);
        DatabaseReference CombineS650Specs = CombineS650.child("Info");
        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myList.add(snapshot.getKey() + ": " + snapshot.getValue() + "\n");
                String List = myList.toString();
                List = List.replace("[", " ").replace("]", "").replace(",", "");
                text.setText(List);
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        CombineS650Specs.addChildEventListener(childEventListener);
        return (text.getText().toString());
    }

}