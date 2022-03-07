package com.learnenglish.studentsvocabulary.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.learnenglish.studentsvocabulary.model.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateUserRequestDto {
    private final String name;
    private final String email;

    @JsonCreator
    public CreateUserRequestDto(@JsonProperty("name") String name, @JsonProperty("email") String email) {
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
