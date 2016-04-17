package com.example.scine.studyfleaux;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class flashcardsHome extends AppCompatActivity{
    private ArrayList<FlashcardSet> cardSetList = new ArrayList<FlashcardSet>();
    public static final String CARDFILE = "FlashcardSetFile";
    private Intent intent;
    Button contextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards_home);
        ListAdapter theAdapter;
        intent = getIntent();
        contextButton  = (Button) findViewById(R.id.contextMenuButton);
        if(intent.hasExtra("cardSet")) {
            addSet(this, (FlashcardSet) intent.getSerializableExtra("cardSet"));
        }
        String[] cardTitles;
        cardSetList = loadCardSet(this);
        String[] noTitles = {"YOU", "SHOULD", "CREATE", "CARDS"};
        if(cardSetList.size() != 0) {
            cardTitles = getTitles();
            theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cardTitles);
        } else {
            theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noTitles);
        }

        ListView theListView = (ListView) findViewById(R.id.cards_list_view);
        theListView.setAdapter(theAdapter);
        registerForContextMenu(theListView);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewCardSet(view, position);
            }
        });

        theListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(flashcardsHome.this, "YOU JUST LONG CLICKED", Toast.LENGTH_SHORT).show();
                return true;
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
        TinyDB tinydb = new TinyDB(this);
        tinydb.putListObject(CARDFILE, cardSetList);
    }

    public ArrayList<FlashcardSet> loadCardSet(Context context) {
        TinyDB tinyDB = new TinyDB(this);
        return tinyDB.getListObject(CARDFILE, FlashcardSet.class);
    }


    private String[] getTitles() {
        String[] temp = new String[cardSetList.size()];
        for(int i=0; i<cardSetList.size(); i++)
            temp[i] = cardSetList.get(i).getTitle();
        return temp;
    }


    public void addSet(Context context, FlashcardSet set) {
        cardSetList = loadCardSet(this);
        cardSetList.add(set);
        saveCardSet(this, cardSetList);
    }

    public void viewCardSet(View view, int pos) {
        Intent intent = new Intent(this, flashcardView.class);
        intent.putExtra("cardSet", cardSetList.get(pos));
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item, int position) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.mnu_edit:
                editSet(info.id, position);
                return true;
            case R.id.mnu_delete:
                deleteSet(info.id, position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void editSet(long id, int position) {
        return;
    }

    private void deleteSet(long id, int position) {
        cardSetList.remove(position);
        saveCardSet(this, cardSetList);
    }


}
