package com.learnenglish.studentsvocabulary.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VocabularyUnitTest {

    @Test
    void vocabularyWithSameIdAreEqual(){
        var vocab1 = new Vocabulary(1,"hello", "Salutation");
        var vocab2 = new Vocabulary(1,"hello", "Salutation");

        assertEquals(vocab1, vocab2);
    }

    @Test
    void booleanAttributesAreFalseByDefault(){
        var vocab = new Vocabulary();

        assertFalse(vocab.isFavorite());
        assertFalse(vocab.isPhrasalVerb());
    }
}