package com.example.marxteamproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class NotesActivity extends AppCompatActivity {

    FloatingActionButton addNoteBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn;
    Query query;
    NoteAdapter noteAdapter;

    FirestoreRecyclerOptions<Note> options;


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

        query  = Utility.getCollectionReferenceForNotes().orderBy("timestamp",Query.Direction.DESCENDING);
        options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query,Note.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(options,this);
        recyclerView.setAdapter(noteAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        noteAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }


}