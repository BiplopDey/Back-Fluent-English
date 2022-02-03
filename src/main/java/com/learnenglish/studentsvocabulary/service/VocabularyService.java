package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.Vocabulary;

import java.util.List;
import java.util.Optional;

public interface VocabularyService {
    public Vocabulary saveVocabulary(Vocabulary vocabulary);
    public List<Vocabulary> getAllVocabularies();
    public void deleteVocabulary(int id);
    public Vocabulary find(int id);

    public Vocabulary update(Vocabulary vocabulary, int id);

    public String greet();
}
