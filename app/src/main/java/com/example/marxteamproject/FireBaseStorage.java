package com.example.marxteamproject;


import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;

public class FireBaseStorage extends FragmentActivity {


    public void mymethod(ImageView rImage, Context context) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Tractors = database.getReference("Tractors");
        DatabaseReference Tractor1032E = Tractors.child("1032E");
        DatabaseReference Tractor1032Ephoto = Tractor1032E.child("Photo");
        DatabaseReference test = database.getReference("Test");
        DatabaseReference test1 = test.child("num");

        test1.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot) {
                        // getting a DataSnapshot for the
                        // location at the specified relative
                        // path and getting in the link variable
                        String link = dataSnapshot.getValue(
                                String.class);
                        Log.w("jf", "Value is: " + link);

                        // loading that data into rImage
                        // variable which is ImageView
                        Glide.with(context)
                                .load(link)
                                .into(rImage);
                        Log.w("rImage", "image requested");


                    }

                    // this will called when any problem
                    // occurs in getting data
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("ImageView", "Retriving image failed");


                    }
                });





    }

}
