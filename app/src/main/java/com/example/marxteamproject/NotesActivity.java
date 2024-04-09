package com.example.marxteamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesActivity extends AppCompatActivity {

    FloatingActionButton addNoteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_screen);

        addNoteBtn = findViewById(R.id.add_note_btn);

        addNoteBtn.setOnClickListener((v)-> startActivity(new Intent(NotesActivity.this, NoteDetailsActivity.class)));
    }
}