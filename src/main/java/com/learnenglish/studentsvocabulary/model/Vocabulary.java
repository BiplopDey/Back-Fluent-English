package com.learnenglish.studentsvocabulary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String definition;
    private boolean favorite = false;
    private boolean phrasalVerb = false;
    @ManyToMany(mappedBy = "vocabularies")
    @JsonIgnore
    private Set<User> students = new HashSet<>();

    public Vocabulary(int id, String name, String definition) {
        this.id = id;
        this.name = name;
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vocabulary))
            return false;
        var other = (Vocabulary) o;
        return id == other.getId();
    }
}
