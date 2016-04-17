package com.example.scine.studyfleaux;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class createFlashcards extends AppCompatActivity {
    private FlashcardSet cardSet;
    private EditText titleEditText, termEditText, definitionEditText;
    private Button addCardButton, saveSetButton;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcards);
        cardSet = new FlashcardSet();
        saveSetButton = (Button) findViewById(R.id.saveButton);
        addCardButton = (Button) findViewById(R.id.addButton);
        termEditText = (EditText) findViewById(R.id.createFlashcardTerm1);
        definitionEditText = (EditText) findViewById(R.id.createFlashcardDefinition);

        saveSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });

        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard(v);
            }
        });
    }

    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);

        titleEditText = (EditText) promptView.findViewById(R.id.createFlashcardTitle1);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                title = titleEditText.getText().toString();
                saveSet(promptView, title);
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void addCard(View v) {
       Flashcard temp = new Flashcard(termEditText.getText().toString(), definitionEditText.getText().toString());
       cardSet.add(temp);
       Toast.makeText(createFlashcards.this, "Card Added!", Toast.LENGTH_SHORT).show();
       termEditText.setText("");
       definitionEditText.setText("");
       termEditText.requestFocus();
    }

    public void saveSet(View v, String tit) {
        Intent intent = new Intent(this, flashcardsHome.class);
        cardSet.setTitle(tit);
        intent.putExtra("cardSet", cardSet);
        startActivity(intent);
    }


}
