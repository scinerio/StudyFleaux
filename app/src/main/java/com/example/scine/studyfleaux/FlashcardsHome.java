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


/**
 * Java class for the homepage flashcard activity
 */
public class FlashcardsHome extends AppCompatActivity {
    /**
     * The list of Flashcard Sets
     */
    private ArrayList<FlashcardSet> cardSetList = new ArrayList<FlashcardSet>();
    /**
     * The Final String which is the name of the private save file
     * Containing the flashcard sets
     */
    public static final String CARDFILE = "FlashcardSetFile";
    /**
     * Used to start other Activities
     */
    private Intent intent;
    /**
     * Will be used to create a floating context menu when
     * A Flashcard set is longclicked
     */
    Button contextButton;


    /**
     * Method that is called when the activity starts
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards_home);
        //Adapter is used to display the list of card sets
        final ListAdapter theAdapter;

        //This intent gets data when a cardset is saved
        intent = getIntent();
        if(intent.hasExtra("cardSet")) {
            addSet(this, (FlashcardSet) intent.getSerializableExtra("cardSet"));
        }
        //String array containing the titles of cardsets
        final String[] cardTitles;

        //Loading the cardsets from the file
        cardSetList = loadCardSet(this);

        //What is displayed if you don't have cards
        final String[] noTitles = {"YOU", "SHOULD", "CREATE", "CARDS"};
        if(cardSetList.size() != 0) {
            cardTitles = getTitles();
            theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cardTitles);
        } else {
            theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noTitles);
        }

        //This is what displays the sets
        final ListView theListView = (ListView) findViewById(R.id.cards_list_view);
        theListView.setAdapter(theAdapter);

        //Prepares the items in the listview for a longclick
        registerForContextMenu(theListView);

        //What happens when you click an item in the list
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(theAdapter.getItem(position).equals(noTitles[position]))
                    Toast.makeText(FlashcardsHome.this, "Create your own!", Toast.LENGTH_SHORT).show();
                else
                    viewCardSet(view, position);
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

    /**
     * Saves the list of cardSets
     * Uses a database library found on github
     * @param context This activity
     * @param list The arraylist of sets
     */
    public void saveCardSet(Context context, ArrayList<FlashcardSet> list) {
        TinyDB tinydb = new TinyDB(this);
        tinydb.putListObject(CARDFILE, list);
    }

    /**
     * Loads the list of cardSets from a file
     * Uses the tinyDB library
     * @param context
     * @return The arraylist
     */
    public ArrayList<FlashcardSet> loadCardSet(Context context) {
        TinyDB tinyDB = new TinyDB(this);
        return tinyDB.getListObject(CARDFILE, FlashcardSet.class);
    }

    /**
     * Method made to get the titles as a String array
     * @return Titles
     */
    private String[] getTitles() {
        String[] temp = new String[cardSetList.size()];
        for(int i=0; i<cardSetList.size(); i++)
            temp[i] = cardSetList.get(i).getTitle();
        return temp;
    }

    /**
     * Adds a set of cards to the cardSet list
     * @param context This activity
     * @param set The set to add
     */
    public void addSet(Context context, FlashcardSet set) {
        cardSetList = loadCardSet(this);
        cardSetList.add(set);
        saveCardSet(this, cardSetList);
    }

    /**
     * Brings you to a viewflashcard activity
     * Displays the sest you clicked on
     * @param view
     * @param pos
     */
    public void viewCardSet(View view, int pos) {
        Intent intent = new Intent(this, flashcardView.class);
        intent.putExtra("cardSet", cardSetList.get(pos));
        startActivity(intent);
    }

    /**
     * Floating context menu
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    /**
     * Item selected in floating context menu
     * @param item
     * @return
     */
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getItemId()) {
            case R.id.mnu_delete:
                deleteSet(info.id, index);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    /**
     * Method to edit the set
     * Should be called when pressed on the floating context menu
     * todo
     * @param id
     */
    private void editSet(long id) {
        return;
    }

    /**
     * Deletes a set from the cardset list
     * Should be called when pressed on the floating context menu
     * @param id
     * @param position
     */
    private void deleteSet(long id, int position) {
        String deleted = cardSetList.get(position).getTitle();
        cardSetList.remove(position);
        Toast.makeText(FlashcardsHome.this, deleted + " deleted", Toast.LENGTH_SHORT).show();
        saveCardSet(this, cardSetList);
        Intent intent = getIntent();
        intent.removeExtra("cardSet");
        finish();
        startActivity(intent);
    }


}
