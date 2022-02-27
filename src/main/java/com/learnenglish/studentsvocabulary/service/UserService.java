package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.User;

import java.util.Optional;

public interface UserService {
    public User getUserById(int id);
}
