package com.example.scine.studyfleaux;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
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


public class flashcardsHome extends AppCompatActivity{
    public ArrayList<FlashcardSet> cardSetList;
    public static final String CARDFILE = "FlashcardSetFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards_home);
        ListAdapter titlesAdapter;
        try {
            cardSetList = loadCardSet(this);
        } catch(Exception e) {
            e.printStackTrace();
        }
        String[] noTitles = {"YOU", "SHOULD", "CREATE", "CARDS"};
        String[] cardTitles = getTitles();
        ListAdapter theAdapter;
        if(cardTitles.length == 0)
            theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noTitles);
        else
            theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noTitles);
        ListView theListView = (ListView) findViewById(R.id.cards_list_view);
        theListView.setAdapter(theAdapter);
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(flashcardsHome.this, "You selected something", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * Directs you to the create flashcard activity
     */
    public void createFlashcards(View view) {
        Intent intent = new Intent(this, createFlashcards.class);
        startActivity(intent);
    }

    public void saveCardSet(Context context, ArrayList<FlashcardSet> list) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        prefsEditor.putString(CARDFILE, json);
        prefsEditor.apply();
    }

    public ArrayList<FlashcardSet> loadCardSet(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("MyObject", "");
        Type type = new TypeToken<List<FlashcardSet>>(){}.getType();
        ArrayList<FlashcardSet> tempList = gson.fromJson(CARDFILE, type);
        return tempList;
    }

    private void fixEmptyTitles() {
        for(int i=0; i<cardSetList.size(); i++) {
            FlashcardSet temp = cardSetList.get(i);
            if(temp.getTitle() == null)
                temp.setTitle("Untitled");
        }
    }

    private String[] getTitles() {
        String[] temp = new String[cardSetList.size()];
        for(int i=0; i<cardSetList.size(); i++)
            temp[i] = cardSetList.get(i).getTitle();
        return temp;
    }


}
