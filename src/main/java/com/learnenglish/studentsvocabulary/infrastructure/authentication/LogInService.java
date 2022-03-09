package com.learnenglish.studentsvocabulary.infrastructure.authentication;

import com.learnenglish.studentsvocabulary.dtos.LoginUserResponseDto;
import com.learnenglish.studentsvocabulary.model.User;

public interface LogInService {
    boolean isLogedIn(int id, String bearerToken);
    LoginUserResponseDto logIn(User user);
}
