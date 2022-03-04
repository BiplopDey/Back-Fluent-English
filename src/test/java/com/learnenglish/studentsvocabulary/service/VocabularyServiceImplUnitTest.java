package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.repository.VocabularyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VocabularyServiceImplUnitTest {
    private VocabularyServiceImpl service;
    private VocabularyRepository repository;
    private Vocabulary vocab;

    @BeforeEach
    void setUp(){
        repository = mock(VocabularyRepository.class);
        service = new VocabularyServiceImpl(repository);
        vocab = new Vocabulary(1,"Hello", "Greeting");
    }

    @Test
    void createVocabulary(){
        when(repository.save(vocab)).thenReturn(vocab);

        var newVocab = service.create(vocab);

        assertEquals(newVocab,vocab);
        assertEquals(newVocab.getName(),vocab.getName());
        verify(repository).save(vocab);
    }

    @Test
    void findVocabulary(){
        when(repository.findById(1)).thenReturn(Optional.of(vocab));

        var newVocab = service.find(1);

        assertEquals(newVocab,vocab);
        assertEquals(newVocab.getName(),vocab.getName());
        verify(repository).findById(1);
    }

    @Test
    void findVocabularyThrowsException(){
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->service.find(1));
        verify(repository).findById(1);
    }

    @Test
    void canUpdate(){
        when(repository.findById(1)).thenReturn(Optional.of(vocab));
        var updateVocab = new Vocabulary(1,"hola", "Saludo");
        when(repository.save(any(Vocabulary.class))).thenReturn(updateVocab);

        var newVocab = service.update(updateVocab,1);

        assertEquals(newVocab,updateVocab);
        assertEquals(newVocab.getName(),updateVocab.getName());
        verify(repository).findById(1);
        verify(repository).save(updateVocab);
    }

    @Test
    void updateFail(){
        when(repository.findById(1)).thenReturn(Optional.empty());
        var updateVocab = new Vocabulary(1,"hola", "Saludo");
        when(repository.save(any(Vocabulary.class))).thenReturn(updateVocab);

        assertThrows(RuntimeException.class, ()->service.update(updateVocab,1));
        verify(repository, times(0)).save(any(Vocabulary.class));
    }
}