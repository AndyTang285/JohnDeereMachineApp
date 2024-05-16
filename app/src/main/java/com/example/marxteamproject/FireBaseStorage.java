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
        if(!TractorModelNum.equals("450E (2023)")){
            this.TractorImage = storageRef.child( "/Tractors/" + TractorModelNum + ".jpg");
        }else{
            this.TractorImage = storageRef.child( "/Tractors/" + TractorModelNum + ".jpeg");
        }


    }

    public void getFirebaseImage(ImageView rImage, Context context) {



        Glide.with(context)
                .load(TractorImage)
                .placeholder(R.drawable.placeholder)
                .into(rImage);

    }


}
