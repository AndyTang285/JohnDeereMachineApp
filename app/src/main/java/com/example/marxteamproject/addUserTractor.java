package com.example.marxteamproject;

import static com.google.firebase.crashlytics.internal.Logger.TAG;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class addUserTractor {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> tractors = new HashMap<>();
    Map<String, Object> currentTractors = new HashMap<>();
    public void saveNoteToFirebase(String tractor, String docId, String tractorType) {
        CollectionReference documentReference;
documentReference =  db.collection("tractors").document("default").collection(docId);
        tractors.put("tractorModelNum",tractor);
        tractors.put("tractorType", tractorType);
        documentReference
                .add(tractors);


        documentReference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                currentTractors.put("data",document.getData());
                                Log.d("Current" , currentTractors.toString());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

        }
        //update note
     //   documentReference = UserTractors.getCollectionReferenceForTractor(docId).document();
       // documentReference.set(tractor);


    });
    }
}