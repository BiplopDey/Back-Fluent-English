package com.learnenglish.studentsvocabulary.model;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String definition;
    private boolean isPhrasalVerb = false;

    public Vocabulary() {
    }

    public Vocabulary(int id, String name, String definition) {
        this.id = id;
        this.name = name;
        this.definition = definition;
    }

    public boolean isPhrasalVerb() {
        return isPhrasalVerb;
    }

    public void setPhrasalVerb(boolean phrasalVerb) {
        isPhrasalVerb = phrasalVerb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
