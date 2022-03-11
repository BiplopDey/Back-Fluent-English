package com.learnenglish.studentsvocabulary.repository;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VocabularyRepository vocabularyRepository;

    User user;
    @BeforeEach
    void setUp(){
        user = new User(1, "foo");
        userRepository.save(user);
    }

    @Test
    void itCanSaveUser(){
        var sut = userRepository.save(user);

        assertThat(user, samePropertyValuesAs(sut));
    }

    @Test
    void itCanFindAnUser(){
        var sut = userRepository.findById(1).get();

        assertThat(user, samePropertyValuesAs(sut));
    }

    @Test
    void itCantFindAnUserAndGiveOptionalWithEmptyUser(){
        var sut = userRepository.findById(2);

        assertFalse(sut.isPresent());
    }

    @Test
    void itCanAttachAnVocabulary(){
        var vocabulary = new Vocabulary(1,"d","p");
        user.addVocabulary(vocabulary);
        userRepository.save(user);

        var sut = vocabularyRepository.findById(1).get().getStudents();

        assertThat(sut, hasItem(user));
    }

    @Test
    void itCanDetachAnVocabularyButNotRemoveIt(){
        var vocabulary = vocabularyRepository.save(new Vocabulary(1,"d","p"));
        user.addVocabulary(vocabulary);
        user = userRepository.save(user);

        user.removeVocabulary(vocabulary);
        userRepository.save(user);

        var sut = vocabularyRepository.findById(1).get().getStudents();
        assertThat(sut, empty());
    }

}