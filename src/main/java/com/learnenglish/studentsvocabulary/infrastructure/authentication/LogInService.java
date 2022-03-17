package com.learnenglish.studentsvocabulary.infrastructure.authentication;

import com.learnenglish.studentsvocabulary.dtos.LoginUserResponseDTO;
import com.learnenglish.studentsvocabulary.model.User;

public interface LogInService {
    boolean isLogedIn(Long id, String bearerToken);
    LoginUserResponseDTO logIn(User user);
}
