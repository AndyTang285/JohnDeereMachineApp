package com.example.marxteamproject;


import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FireBaseStorage extends FragmentActivity {

    public void FirebaseImage(ImageView rImage, Context context, String TractorPhoto) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference TractorImage = storageRef.child( "/Tractors/" + TractorPhoto + ".jpg");
        Glide.with(context)
                .load(TractorImage)
                .placeholder(R.drawable.placeholder)
                .into(rImage);




    }


}
