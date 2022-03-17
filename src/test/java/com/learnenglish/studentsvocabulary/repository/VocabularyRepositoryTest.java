package com.learnenglish.studentsvocabulary.repository;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VocabularyRepositoryTest {

    @Autowired
    private VocabularyRepository vocabularyRepository;

    List<Vocabulary> vocabList;
    @BeforeEach
    void setUp(){
        vocabList =
        vocabularyRepository.saveAll(List.of(
                new Vocabulary("d","s"),
                new Vocabulary("d","s"),
                new Vocabulary("d","s")
        ));
    }

    @AfterEach
    void cleanDb(){
        vocabularyRepository.deleteAll();
    }

    @Test
    void itCanGetAllVocabularies(){
        var sut = vocabularyRepository.findAll();

        assertThat(sut, hasSize(3));
    }

    @Test
    void itCanDeleteAVocabulary(){
        vocabularyRepository.deleteById(vocabList.get(0).getId());

        var sut = vocabularyRepository.findAll();

        assertThat(sut, hasSize(2));
    }
}