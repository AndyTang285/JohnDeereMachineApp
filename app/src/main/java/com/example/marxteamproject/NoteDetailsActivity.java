package com.example.marxteamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class NoteDetailsActivity extends AppCompatActivity {
    EditText titleEditText;
    EditText contentEditText;
    ImageButton saveNoteBtn;
    TextView pageTitleTextView;
    String title;
    String content;
    String docId;
    Boolean isEditMode = false;
    Button deleteNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_details_screen);

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        pageTitleTextView = findViewById(R.id.notes_page_title);
        deleteNoteBtn = findViewById(R.id.delete_note_btn);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");

        if (docId!=null && !docId.isEmpty()) {
            isEditMode = true;
        }

        titleEditText.setText(title);
        contentEditText.setText(content);

        if(isEditMode) {
            pageTitleTextView.setText("Edit your note");
            deleteNoteBtn.setVisibility(View.VISIBLE);
        }

        Utility.showToast(NoteDetailsActivity.this, "test");
        saveNoteBtn.setOnClickListener((v)-> saveNote());

        deleteNoteBtn.setOnClickListener((v)-> deleteNoteFromFirebase());

    }

    public void saveNote() {

        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();

        if (noteTitle==null || noteContent.isEmpty() ) {
            Utility.showToast(NoteDetailsActivity.this, "test4");
            titleEditText.setError("Title is required");
            return;
        }
        Utility.showToast(NoteDetailsActivity.this, "test5");

        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        saveNoteToFirebase(note);
    }

    public void saveNoteToFirebase(Note note) {

        DocumentReference documentReference;

        if (isEditMode) {
            //update note
            documentReference = Utility.getCollectionReferenceForNotes().document(docId);
        } else {
            //create new note
            documentReference = Utility.getCollectionReferenceForNotes().document();
        }

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    //note is added
                    Utility.showToast(NoteDetailsActivity.this, "Note added successfully");
                    finish();
                } else {
                    //note adding failed
                    Utility.showToast(NoteDetailsActivity.this, "Failed while adding note");
                }
            }
        });

    }

    public void deleteNoteFromFirebase(){

        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForNotes().document(docId);
        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    //note is deleted
                    Utility.showToast(NoteDetailsActivity.this, "Note deleted successfully");
                    finish();
                } else {
                    //note adding failed
                    Utility.showToast(NoteDetailsActivity.this, "Failed while deleting note");
                }
            }
        });

    }

}