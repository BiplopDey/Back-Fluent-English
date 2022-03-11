package com.learnenglish.studentsvocabulary.repository;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
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

    @Test
    void itCanGetAllVocabularies(){
        vocabularyRepository.saveAll(List.of(
                new Vocabulary(1,"d","s"),
                new Vocabulary(2,"d","s"),
                new Vocabulary(3,"d","s")
                ));

        var allVocab = vocabularyRepository.findAll();

        assertThat(allVocab, hasSize(3));
    }
}