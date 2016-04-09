package com.example.scine.studyfleaux;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class createFlashcards extends AppCompatActivity {
    private FlashcardSet cardSet;
    private EditText title, term, definition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcards);

        cardSet = new FlashcardSet();
    }

    public void saveFlashcard(View view) {

        title = (EditText) findViewById(R.id.createFlashcardTitle);
        term = (EditText) findViewById(R.id.createFlashcardTerm);
        definition = (EditText) findViewById(R.id.createFlashcardDefinition);

        cardSet.setTitle(title.getText().toString());
        cardSet.add(new Flashcard(term.getText().toString(), definition.getText().toString()));
        Toast toast = Toast.makeText(getApplicationContext(), "Added Flashcard", Toast.LENGTH_SHORT);
        toast.show();
    }
}
