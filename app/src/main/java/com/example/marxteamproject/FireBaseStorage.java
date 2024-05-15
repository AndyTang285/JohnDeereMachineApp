package com.example.marxteamproject;


import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FireBaseStorage extends FragmentActivity {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    private StorageReference TractorImage;

    public void setFirebaseImage(String TractorModelNum){

         this.TractorImage = storageRef.child( "/Tractors/" + TractorModelNum + ".jpg");

    }

    public void getFirebaseImage(ImageView rImage, Context context) {



        Glide.with(context)
                .load(TractorImage)
                .placeholder(R.drawable.placeholder)
                .into(rImage);

    }


}
