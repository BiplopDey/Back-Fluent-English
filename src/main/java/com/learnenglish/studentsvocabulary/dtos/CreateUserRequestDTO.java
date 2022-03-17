package com.learnenglish.studentsvocabulary.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.learnenglish.studentsvocabulary.model.User;
import lombok.Getter;

@Getter
public class CreateUserRequestDTO {
    private final String name;
    private final String email;

    @JsonCreator
    public CreateUserRequestDTO(@JsonProperty("name") String name, @JsonProperty("email") String email) {
        this.name = name;
        this.email = email;
    }

    public User getUser(){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return user;
    }
}
