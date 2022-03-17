package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.Vocabulary;

import java.util.List;
import java.util.Optional;

public interface VocabularyService {
    Vocabulary create(Vocabulary vocabulary);
    List<Vocabulary> all();
    void delete(Long id);
    Vocabulary find(Long id);
    Vocabulary update(Vocabulary vocabulary, Long id);

    public String greet();
}
