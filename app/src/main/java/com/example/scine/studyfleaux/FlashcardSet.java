package com.example.scine.studyfleaux;


import java.io.Serializable;
import java.util.ArrayList;


public class FlashcardSet implements Serializable{
    private ArrayList<Flashcard> list;
    private String title;

    public FlashcardSet() {
        list = new ArrayList<>();
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return list.size();
    }

    public Flashcard get(int pos) {
        return list.get(pos);
    }

}
