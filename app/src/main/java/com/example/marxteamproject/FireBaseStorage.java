package com.example.marxteamproject;

import static com.squareup.picasso.Picasso.*;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.*;
import com.squareup.picasso.Picasso;
import org.checkerframework.checker.nullness.qual.NonNull;

public class FireBaseStorage extends DatabaseActivity {
    ImageView rImage;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.tractor_screen);

        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        DatabaseReference Tractors = database.getReference("Tractors");
        DatabaseReference Tractor1032E = Tractors.child("1032E");
        DatabaseReference Tractor1032Ephoto = Tractor1032E.child("Photo");
        rImage = findViewById(R.id.Tractor_image_1);
        Tractor1032Ephoto.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot) {
                        // getting a DataSnapshot for the
                        // location at the specified relative
                        // path and getting in the link variable
                        String link = dataSnapshot.getValue(
                                String.class);

                        // loading that data into rImage
                        // variable which is ImageView
                        get()
                                .load(link)
                                .into(rImage);
                    }

                    // this will called when any problem
                    // occurs in getting data
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError) {
                        // we are showing that error message in
                        // toast

                    }
                });


    }

}
