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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

public class FireBaseStorage extends FragmentActivity {


    public void mymethod(ImageView rImage, Context context) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
       StorageReference storageReference = storageRef.child("/Tractors/1032E/1032E.avif");

        Glide.with(context)
                .load(storageReference)
                .placeholder(R.drawable.placeholder)
                .into(rImage);




    }


}
