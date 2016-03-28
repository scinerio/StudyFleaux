package com.example.scine.studyfleaux;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class notesHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] notes = {"English Midterm", "Astronomy Final", "Computer Science Iterations", "Numerioal Method Lab", "Some random subject", "AgileMethods", "Boredom",
                            "douglas pls", "i miss duncan", "apps r cool", "the office is fun", "apps are hard", "i am bored", "wtf is this", "random strings"};
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        final ListView notesListView = (ListView) findViewById(R.id.notesListView);
        notesListView.setAdapter(adapter);
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String notePicked = "You chose " +
                        String.valueOf(notesListView.getItemAtPosition(position));
                Toast.makeText(notesHome.this, notePicked, Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void createNotes(View view) {
        Intent intent = new Intent(this, createNotes.class);
        startActivity(intent);
    }
}
