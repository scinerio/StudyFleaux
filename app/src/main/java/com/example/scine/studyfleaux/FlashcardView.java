package com.example.scine.studyfleaux;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class flashcardView extends Activity {
    private FlashcardSet cardSet;
    private ArrayList<FlashcardSet> cardSetList;
    private TextView flashTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view);
        cardSetList = flashcardsHome.loadCards(this);
        cardSet = cardSetList.get(getIntent().getExtras().getInt("position"));
        flashTitleView = (TextView) findViewById(R.id.view_flashcard_title_textView);
        flashTitleView.setText(cardSet.getTitle());
    }
}
