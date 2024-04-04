package com.example.marxteamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

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

        if (noteTitle==null || noteContent.isEmpty()) {
            titleEditText.setError("Please enter a title");
            return;
        }

        Intent intent = new Intent(NotesDetailedActivity.this, NotesActivity.class);
        startActivity(intent);



    }
}