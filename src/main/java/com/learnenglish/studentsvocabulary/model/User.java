package com.learnenglish.studentsvocabulary.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public User() {
    }

    @ManyToMany
    Set<Vocabulary> vocabularies = new HashSet<>();

    public Set<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(Set<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
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
}
