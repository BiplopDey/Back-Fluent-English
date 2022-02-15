package com.learnenglish.studentsvocabulary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String definition;
    private boolean isPhrasalVerb = false;

    @ManyToMany(mappedBy = "vocabularies")
    @JsonIgnore
    private Set<User> students = new HashSet<>();

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

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
