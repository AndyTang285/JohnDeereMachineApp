package com.example.marxteamproject;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class UserTractors {


    static CollectionReference getCollectionReferenceForTractor(String docId) {
        FirebaseUser currentUser =  FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            return FirebaseFirestore.getInstance().collection("tractors")
                    .document(currentUser.getUid()).collection(docId);
        }
        else return FirebaseFirestore.getInstance().collection("tractors")
                .document("default").collection(docId);
    }

    static String timestampToString(Timestamp timestamp) {
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }

}