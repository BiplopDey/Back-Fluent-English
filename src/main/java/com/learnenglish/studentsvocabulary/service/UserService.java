package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;

public interface UserService {
    User find(int id);
    void delete(int id);
    void addVocabulary(int id, Vocabulary vocabulary);
    void detachVocabulary(int id, Vocabulary vocabulary);
    User create(User user);
}
