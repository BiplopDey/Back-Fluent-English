package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.User;

public interface TokenService {
    String generate(User user);
    boolean exists(String token);
    User getUser(String token);
}
