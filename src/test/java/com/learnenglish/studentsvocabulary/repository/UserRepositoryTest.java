package com.learnenglish.studentsvocabulary.repository;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void cleanDb(){
        userRepository.deleteAll();
        vocabularyRepository.deleteAll();
    }

    @Test
    void itCanSaveUser(){
        User user = new User("bazz", "foo");
        var sut = userRepository.save(user);

        assertEquals(user.getName(),sut.getName());
        assertEquals(user.getEmail(),sut.getEmail());
    }

    @Test
    void itCanFindAnUser(){
        User user = userRepository.save(new User("bazz", "foo"));

        var sut = userRepository.findById(user.getId()).get();

        assertEquals(user.getName(),sut.getName());
        assertEquals(user.getEmail(),sut.getEmail());
    }

    @Test
    void itCantFindAnUserAndGiveOptionalWithEmptyUser(){
        User user = userRepository.save(new User("bazz", "foo"));

        var sut = userRepository.findById(user.getId()+1);

        assertFalse(sut.isPresent());
    }

    @Test
    void itCanAttachAnVocabulary(){
        User user = userRepository.save(new User("bazz", "foo"));
        var vocabulary = vocabularyRepository.save(new Vocabulary("d","p"));
        user.addVocabulary(vocabulary);
        userRepository.save(user);

        var sut = vocabularyRepository.findById(vocabulary.getId()).get().getStudents();

        assertThat(sut, hasItem(user));
    }

    @Test
    void itCanDetachAnVocabularyButNotRemoveIt(){
        User user = userRepository.save(new User("bazz", "foo"));
        var vocabulary = vocabularyRepository.save(new Vocabulary("d","p"));
        user.addVocabulary(vocabulary);
        user = userRepository.save(user);
        user.removeVocabulary(vocabulary);
        userRepository.save(user);

        var sut = vocabularyRepository.findById(vocabulary.getId()).get().getStudents();

        assertThat(sut, empty());
    }

}