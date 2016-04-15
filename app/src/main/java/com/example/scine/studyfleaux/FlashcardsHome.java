package com.example.scine.studyfleaux;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class flashcardsHome extends AppCompatActivity {
    private ArrayList<FlashcardSet> cardSetList;
    public static final String CARDFILE = "FlashcardSetFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards_home);
        String[] stuff = {"stuff","stuff","stuff","stuff","stuff",};
        String[] cardTitles;
        ListAdapter theAdapter;
        //Read/Create File
        try{
            cardSetList = loadCards(this);
            fixEmptyTitles();
            if(!cardSetList.isEmpty()) {
                cardTitles = new String[cardSetList.size()];
                for (int i = 0; i < cardSetList.size(); i++) {
                    cardTitles[i] = cardSetList.get(i).getTitle();
                }
                theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cardTitles);
            }
            else {
                theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stuff);
            }

            ListView cardsListView = (ListView) findViewById(R.id.cards_list_view);
            cardsListView.setAdapter(theAdapter);
            cardsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent viewCardIntent = new Intent(flashcardsHome.this, flashcardView.class);
                    viewCardIntent.putExtra("pos", position);
                    startActivity(viewCardIntent);
                    finish();
                }
            });
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Directs you to the create flashcard activity
     */
    public void createFlashcards(View view) {
        Intent intent = new Intent(this, createFlashcards.class);
        startActivity(intent);
    }

    public static void saveCards(Context context, ArrayList<FlashcardSet> cards) {
        SharedPreferences mPrefs = context.getSharedPreferences(CARDFILE, context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cards);
        prefsEditor.putString("myJson", json);
        prefsEditor.commit();
    }

    public static ArrayList<FlashcardSet> loadCards(Context context) {
        ArrayList<FlashcardSet> cardlist = new ArrayList<FlashcardSet>();
        SharedPreferences mPrefs = context.getSharedPreferences(CARDFILE, context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("myJson", "");
        if (json.isEmpty()) {
            cardlist = new ArrayList<FlashcardSet>();
        } else {
            Type type = new TypeToken<List<FlashcardSet>>() {
            }.getType();
            cardlist = gson.fromJson(json, type);
        }
        return cardlist;
    }

    private void fixEmptyTitles() {
        for(int i=0; i<cardSetList.size(); i++) {
            FlashcardSet temp = cardSetList.get(i);
            if(temp.getTitle() == null)
                temp.setTitle("Untitled");
        }
    }

}
