package com.example.marxteamproject;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class addUserTractor {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static int i = 0;

    private static CollectionReference documentReference;
    public static Map<String, Object> tractors = new HashMap<>();
    private static  Map<String, Object> currentTractors = new HashMap<>();

    public void saveNoteToFirebase(String tractor, String docId) {

        documentReference = db.collection("tractors").document("default").collection(docId);
        tractors.put("tractorModelNum", tractor);
        // tractors.put("tractorType", tractorType);
        documentReference
                .add(tractors);
    }

    //update note
//   documentReference = UserTractors.getCollectionReferenceForTractor(docId).document();
// documentReference.set(tractor);
    public synchronized Map<String, Object> getTractorNum() {
        documentReference = FirebaseFirestore.getInstance().collection("tractors").document("default").collection("work");


        documentReference
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            currentTractors.put(String.valueOf(i), (Objects.requireNonNull(document.get("tractorModelNum"))).toString());

                        Log.d("Current", currentTractors.toString());

                            i++;

                        }

                    } else {
                       Log.w( "Error getting documents.", task.getException());
                    }

                });
        return currentTractors;

    }
   /* public static boolean getNum(){
        if()



        return true;
        */
}