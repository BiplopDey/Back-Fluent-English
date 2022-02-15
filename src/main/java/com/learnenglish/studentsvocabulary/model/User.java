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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_VOCABULARY",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "VOCABULARY_ID")})
    Set<Vocabulary> vocabularies = new HashSet<>();

    public void addVocabulary(Vocabulary vocabulary){
        vocabularies.add(vocabulary);
        vocabulary.getStudents().add(this);
    }

    public void removeVocabulary(Vocabulary vocabulary){
        vocabularies.remove(vocabulary);
        vocabulary.getStudents().remove(this);
    }

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
