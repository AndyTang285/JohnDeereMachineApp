package com.example.marxteamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tractor_screen);

        listView = findViewById(R.id.status_info_text);
      ArrayList<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.tractor_screen, R.id.status_info_text, list);
        listView.setAdapter(adapter);
       FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tractorsRef = FirebaseDatabase.getInstance().getReference().child("Tractors");


        DatabaseReference tractors1032E = tractorsRef.child("1032E");
        DatabaseReference tractors103E2MaintenanceInfo = tractors1032E.child("Maintenance");
        DatabaseReference tractors103E2PartsInfo = tractors1032E.child("Parts");
        DatabaseReference tractors103E2SpecsInfo = tractors1032E.child("Specs");




       FirebaseDatabase.getInstance().getReference("tractors103E2SpecsInfo").addValueEventListener(new ValueEventListener() {
           @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
        list.add((String) snapshot.getValue());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
            });



    }
}