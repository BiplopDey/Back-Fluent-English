package com.learnenglish.studentsvocabulary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String definition;
    private boolean favorite = false;
    private boolean phrasalVerb = false;
    @ManyToMany(mappedBy = "vocabularies")
    @JsonIgnore
    private Set<User> students = new HashSet<>();

    public Vocabulary(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vocabulary))
            return false;
        var other = (Vocabulary) o;
        return id != null &&
                Objects.equals(id, other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
