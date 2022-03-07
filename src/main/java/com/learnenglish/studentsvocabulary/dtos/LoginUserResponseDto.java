package com.learnenglish.studentsvocabulary.dtos;

import lombok.Getter;

@Getter
public class LoginUserResponseDto {
    private final int id;
    private final String accessToken;

    public LoginUserResponseDto(int id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }

}
