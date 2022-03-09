package com.learnenglish.studentsvocabulary.infrastructure.authentication;

import com.learnenglish.studentsvocabulary.model.User;

public interface TokenService {
    String generate(User user);
    boolean exists(String token);
    User getUser(String token);
}

