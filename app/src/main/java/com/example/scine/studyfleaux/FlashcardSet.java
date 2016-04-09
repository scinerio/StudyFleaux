package com.example.scine.studyfleaux;

import java.util.ArrayList;


public class FlashcardSet {
    private ArrayList<Flashcard> list;
    private String title;

    public FlashcardSet(String title) {
        list = new ArrayList<>();
        this.title = title;
    }

    public void add(Flashcard card) {
        list.add(card);
    }

    public void remove(int pos) {
        list.remove(pos);
    }

    public void edit(int pos, String term, String definition) {
        Flashcard newCard = new Flashcard(term, definition);
        list.set(pos, newCard);
    }
}
