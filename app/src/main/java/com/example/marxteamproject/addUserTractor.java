package com.example.marxteamproject;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.auth.User;
import org.checkerframework.checker.nullness.qual.NonNull;

public class addUserTractor {


    public void saveNoteToFirebase(String tractor, String docId) {
        DocumentReference documentReference;

        //update note
        documentReference = UserTractors.getCollectionReferenceForTractor(docId).document();
        documentReference.set(tractor);


    }
}

