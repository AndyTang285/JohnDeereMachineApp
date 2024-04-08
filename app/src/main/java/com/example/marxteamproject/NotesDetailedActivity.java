package com.example.marxteamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class NotesDetailedActivity extends AppCompatActivity {
    EditText titleEditText;
    EditText contentEditText;
    ImageButton saveNotesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_detailed);

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNotesBtn = findViewById(R.id.save_notes_btn);

        saveNotesBtn.setOnClickListener( (v)-> saveNote());
    }


    public void saveNote() {
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();

//        if (noteTitle==null || noteContent.isEmpty()) {
//            titleEditText.setError("Please enter a title");
//            return;
//        }

        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimeStamp(Timestamp.now());

        saveNoteToFirebase(note);
    }

    public void saveNoteToFirebase(Note note) {

        DocumentReference documentReference;

        documentReference = Utility.getCollectionReferenceForNotes().document();

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()) {
                    //note is added
                    Utility.showToast(NotesDetailedActivity.this,"Note added successfully");
                    finish();
                } else {
                    Utility.showToast(NotesDetailedActivity.this, "Failed while adding note");
                }
            }
        });



    }

}