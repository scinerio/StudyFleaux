package com.example.scine.studyfleaux;


public class Flashcard {
    private String term, definition;

    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String toString() {
        return term + "\n" + definition;
    }
}
