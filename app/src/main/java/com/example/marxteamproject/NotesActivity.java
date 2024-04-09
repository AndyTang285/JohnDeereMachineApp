package com.example.marxteamproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesActivity extends AppCompatActivity {

    FloatingActionButton addNoteBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_screen);

        addNoteBtn = findViewById(R.id.add_note_btn);
        recyclerView = findViewById(R.id.notes_recycler_view);
        menuBtn = findViewById(R.id.notes_menu_btn);

        addNoteBtn.setOnClickListener((v)-> startActivity(new Intent(NotesActivity.this, NoteDetailsActivity.class)));
        menuBtn.setOnClickListener((v)-> showMenu());
        setUpRecyclerView();
    }

    public void showMenu() {
        //display menu
    }

    public void setUpRecyclerView() {

    }
}