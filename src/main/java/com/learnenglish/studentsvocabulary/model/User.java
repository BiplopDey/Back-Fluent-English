package com.learnenglish.studentsvocabulary.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_VOCABULARY",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "VOCABULARY_ID")})
    Set<Vocabulary> vocabularies = new HashSet<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public void addVocabulary(Vocabulary vocabulary){
        vocabularies.add(vocabulary);
        vocabulary.getStudents().add(this);
    }

    public void removeVocabulary(Vocabulary vocabulary){
        vocabularies.remove(vocabulary);
        vocabulary.getStudents().remove(this);
    }
}
