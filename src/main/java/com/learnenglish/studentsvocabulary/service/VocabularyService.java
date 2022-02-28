package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.Vocabulary;

import java.util.List;
import java.util.Optional;

public interface VocabularyService {
    Vocabulary create(Vocabulary vocabulary);
    List<Vocabulary> all();
    void delete(int id);
    Vocabulary find(int id);
    Vocabulary update(Vocabulary vocabulary, int id);

    public String greet();
}
