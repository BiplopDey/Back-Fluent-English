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
        vocab = new Vocabulary("Hello", "Greeting");
        vocab.setId(1L);
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
        when(repository.findById(vocab.getId())).thenReturn(Optional.of(vocab));

        var newVocab = service.find(vocab.getId());

        assertEquals(newVocab,vocab);
        assertEquals(newVocab.getName(),vocab.getName());
        verify(repository).findById(vocab.getId());
    }

    @Test
    void findVocabularyThrowsException(){
        when(repository.findById(vocab.getId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->service.find(vocab.getId()));
        verify(repository).findById(vocab.getId());
    }

    @Test
    void canUpdate(){
        when(repository.findById(vocab.getId())).thenReturn(Optional.of(vocab));
        var updateVocab = new Vocabulary("hola", "Saludo");
        when(repository.save(any(Vocabulary.class))).thenReturn(updateVocab);

        var newVocab = service.update(updateVocab,vocab.getId());

        assertEquals(newVocab,updateVocab);
        assertEquals(newVocab.getName(),updateVocab.getName());
        verify(repository).findById(vocab.getId());
        verify(repository).save(updateVocab);
    }

    @Test
    void updateFail(){
        when(repository.findById(vocab.getId())).thenReturn(Optional.empty());
        var updateVocab = new Vocabulary("hola", "Saludo");
        when(repository.save(any(Vocabulary.class))).thenReturn(updateVocab);

        assertThrows(RuntimeException.class, ()->service.update(updateVocab, vocab.getId()));
        verify(repository, times(0)).save(any(Vocabulary.class));
    }
}