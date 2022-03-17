package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;

import java.util.Set;

public interface UserService {
    User find(Long id);
    void delete(Long id);
    void addVocabulary(Long id, Vocabulary vocabulary);
    void detachVocabulary(Long id, Vocabulary vocabulary);
    User create(User user);
    Set<Vocabulary> getVocabularies(Long id);
}
