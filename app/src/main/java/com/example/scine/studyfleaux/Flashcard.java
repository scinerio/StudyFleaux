package com.example.scine.studyfleaux;


import java.io.Serializable;

public class Flashcard implements Serializable{
    private String term, definition;

    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String toString() {
        return term + "\n" + definition;
    }
}
