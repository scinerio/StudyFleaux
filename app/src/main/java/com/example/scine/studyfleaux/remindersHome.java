package com.example.scine.studyfleaux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

public class remindersHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] reminders = {"Eat breakfast", "Eat lunch", "Eat dinner", "Eat Dessert", "Write essay", "Iterate life", "Program", "Sleep", "Repeat", "Browse dank memes"};
        final GridView remGridView = (GridView) findViewById(R.id.classesGridView);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, reminders);
        remGridView.setAdapter(adapter);
        remGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String reminderPicked = "You chose " + String.valueOf(remGridView.getItemAtPosition(position));
                Toast.makeText(remindersHome.this, reminderPicked, Toast.LENGTH_SHORT).show();
            }
        });
    }



}
