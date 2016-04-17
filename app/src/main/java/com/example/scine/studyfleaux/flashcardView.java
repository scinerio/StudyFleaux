package com.example.scine.studyfleaux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class flashcardView extends Activity {
    private FlashcardSet cardSet;
    private ArrayList<FlashcardSet> cardSetList;
    private TextView flashTitleView, flashTermView, flashDefView;
    private Button nextButton;
    public static int i;
    public static boolean termTime = false;

    public flashcardView() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view);
        Intent intent = getIntent();
        cardSet = (FlashcardSet) intent.getSerializableExtra("cardSet");
        flashTitleView = (TextView) findViewById(R.id.view_flashcard_title_textView);
        flashTermView = (TextView) findViewById(R.id.view_flashcard_term_textView);
        flashDefView = (TextView) findViewById(R.id.view_flashcard_def_textView);
        nextButton = (Button) findViewById(R.id.nextButton);
        flashTitleView.setText(cardSet.getTitle());
        flashTermView.setText(cardSet.get(0).getTerm());
        i=0;
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i >= cardSet.getSize())
                    i = 0;
                else if(termTime) {
                    flashTermView.setText(cardSet.get(i).getTerm());
                    flashDefView.setText("");
                    termTime = false;
                }
                else {
                    flashDefView.setText(cardSet.get(i).getDefinition());
                    termTime = true;
                    i++;
                }
            }
        });
    }
}
