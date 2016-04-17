package com.example.scine.studyfleaux;


import java.io.Serializable;

public class Flashcard implements Serializable{
    private String term, definition;

    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String toString() {
        return term + "\n" + definition;

    }
}
