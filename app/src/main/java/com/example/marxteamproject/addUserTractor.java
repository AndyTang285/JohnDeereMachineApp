package com.example.marxteamproject;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class addUserTractor {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static int i = 0;
    public static String page;

    private static CollectionReference documentReference;
    private static DocumentReference collectionReference;
    public static Map<String, Object> tractors = new HashMap<>();
    private static Map<String, Object> currentTractors = new HashMap<>();

public static void setScreen(boolean work){
    if(work) {
            page = "work";
        }
    else{
        page = "home";
    }
    }

    public void saveNoteToFirebase(String tractor, String docId) {
        FirebaseUser currentUser =  FirebaseAuth.getInstance().getCurrentUser();
        assert currentUser != null;
        documentReference = db.collection("tractors").document(currentUser.getUid()).collection(page);
        tractors.put("tractorModelNum", tractor);
        // tractors.put("tractorType", tractorType);
        documentReference
                .add(tractors);
    }

    //update note
//   documentReference = UserTractors.getCollectionReferenceForTractor(docId).document();
// documentReference.set(tractor);
    public static synchronized Map<String, Object> getTractorNum() {
        FirebaseUser currentUser =  FirebaseAuth.getInstance().getCurrentUser();


        assert currentUser != null;
        documentReference = FirebaseFirestore.getInstance().collection("tractors").document(currentUser.getUid()).collection(page);


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
    /*
    public static void deleteTractor(String tractor){
        FirebaseUser currentUser =  FirebaseAuth.getInstance().getCurrentUser();
        assert currentUser != null;
        collectionReference = FirebaseFirestore.getInstance().collection("tractors").document(currentUser.getUid()).collection(page).document(tractor);
        collectionReference.delete();
    }

     */
}