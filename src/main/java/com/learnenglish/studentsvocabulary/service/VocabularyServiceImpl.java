package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class VocabularyServiceImpl implements VocabularyService{
    private final VocabularyRepository vocabularyRepository;

    public VocabularyServiceImpl(final VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    @Override
    public Vocabulary create(Vocabulary vocabulary) {
        return vocabularyRepository.save(vocabulary);
    }

    @Override
    public List<Vocabulary> all() {
        return vocabularyRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        vocabularyRepository.deleteById(id);
    }

    @Override
    public Vocabulary find(Long id) {
        return vocabularyRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Vocabulary not found"));
    }

    @Override
    public Vocabulary update(Vocabulary vocabulary, Long id) {
        var vocabToUpdate = find(id);

        vocabulary.setId(vocabToUpdate.getId());

        return vocabularyRepository.save(vocabulary);
    }

    @Override
    public String greet() {
        return "Hello";
    }

}
