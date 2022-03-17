package com.learnenglish.studentsvocabulary.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserResponseDTO {
    private final Long id;
    private final String accessToken;
}
