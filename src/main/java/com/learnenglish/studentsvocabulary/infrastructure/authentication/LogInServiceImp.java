package com.learnenglish.studentsvocabulary.infrastructure.authentication;

import com.learnenglish.studentsvocabulary.dtos.LoginUserResponseDto;
import com.learnenglish.studentsvocabulary.model.User;

public class LogInServiceImp implements LogInService {
    private final TokenService tokenService;

    public LogInServiceImp(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean isLogedIn(int id, String bearerToken) {
        String token = parseToken(bearerToken);
        return tokenService.exists(token) ? tokenService.getUser(token).getId() == id : false;
    }

    @Override
    public LoginUserResponseDto logIn(User user) {
        return new LoginUserResponseDto(user.getId(), tokenService.generate(user));
    }

    private String parseToken(String bearerToken){
        return bearerToken.split("\\s+")[1];
    }

}
