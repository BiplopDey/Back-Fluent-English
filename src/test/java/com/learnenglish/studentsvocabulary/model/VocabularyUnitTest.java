package com.learnenglish.studentsvocabulary.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VocabularyUnitTest {

    @Test
    void vocabularyWithSameIdAreEqual(){
        var vocab1 = new Vocabulary("hello", "Salutation");
        var vocab2 = new Vocabulary("hello1", "Salutation1");
        vocab1.setId(1L);
        vocab2.setId(1L);

        assertEquals(vocab1, vocab2);
    }

    @Test
    void vocabularyWithUnimplementIDAreNotEqual(){
        var vocab1 = new Vocabulary("hello", "Salutation");
        var vocab2 = new Vocabulary("hello1", "Salutation1");

        assertNotEquals(vocab1, vocab2);
    }

    @Test
    void booleanAttributesAreFalseByDefault(){
        var vocab = new Vocabulary();

        assertFalse(vocab.isFavorite());
        assertFalse(vocab.isPhrasalVerb());
    }
}