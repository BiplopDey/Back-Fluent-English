package com.learnenglish.studentsvocabulary.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_VOCABULARY",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "VOCABULARY_ID")})
    Set<Vocabulary> vocabularies = new HashSet<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addVocabulary(Vocabulary vocabulary){
        vocabularies.add(vocabulary);
        vocabulary.getStudents().add(this);
    }

    public void removeVocabulary(Vocabulary vocabulary){
        vocabularies.remove(vocabulary);
        vocabulary.getStudents().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User))
            return false;
        var other = (User) o;
        return id != null &&
                Objects.equals(id, other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
