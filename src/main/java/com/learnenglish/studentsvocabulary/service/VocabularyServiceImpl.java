package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VocabularyServiceImpl implements VocabularyService{
    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Override
    public Vocabulary saveVocabulary(Vocabulary vocabulary) {
        return vocabularyRepository.save(vocabulary);
    }
}
