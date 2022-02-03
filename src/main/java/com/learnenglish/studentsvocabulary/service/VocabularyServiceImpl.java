package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VocabularyServiceImpl implements VocabularyService{
    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Override
    public Vocabulary saveVocabulary(Vocabulary vocabulary) {
        return vocabularyRepository.save(vocabulary);
    }

    @Override
    public List<Vocabulary> getAllVocabularies() {
        return vocabularyRepository.findAll();
    }



    @Override
    public void deleteVocabulary(int id) {
        vocabularyRepository.deleteById(id);
    }

    @Override
    public Vocabulary find(int id) {
        var vocabulary = vocabularyRepository.findById(id);
        if(!vocabulary.isPresent()) return null;
        return vocabulary.get();
    }

    @Override
    public Vocabulary update(Vocabulary vocabulary, int id) {
        if(!vocabularyRepository.existsById(id)) return null;
        vocabulary.setId(id);
        return vocabularyRepository.save(vocabulary);
    }

    @Override
    public String greet() {
        return "Hello";
    }

}
