package com.example.scine.studyfleaux;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class createFlashcards extends AppCompatActivity {
    private FlashcardSet cardSet;
    private EditText title, term, definition;
    private ArrayList<FlashcardSet> cardSetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcards);
        cardSet = new FlashcardSet();
        cardSetList = flashcardsHome.loadCards(this);
    }

    public void addCard(View view) {
        title = (EditText) findViewById(R.id.createFlashcardTitle);
        term = (EditText) findViewById(R.id.createFlashcardTerm);
        definition = (EditText) findViewById(R.id.createFlashcardDefinition);
        cardSet.setTitle(title.getText().toString());
        Flashcard temp = new Flashcard(term.getText().toString(), definition.getText().toString());
        cardSet.add(temp);
        Toast.makeText(createFlashcards.this, "Card added!", Toast.LENGTH_SHORT).show();
    }

    public void saveSet(View view) {
        cardSetList.add(cardSet);
        flashcardsHome.saveCards(this, cardSetList);
        Intent intent = new Intent(this, flashcardsHome.class);
        startActivity(intent);
    }
}
