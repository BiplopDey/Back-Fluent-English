package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.service.BearerTokenService;
import com.learnenglish.studentsvocabulary.service.TokenService;
import com.learnenglish.studentsvocabulary.service.UserService;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerUnitTest {

    private UserController userController;
    private UserService userService;
    private VocabularyService vocabularyService;

    @BeforeEach
    void setUp(){
        userService = mock(UserService.class);
        vocabularyService = mock(VocabularyService.class);
        userController = new UserController(userService, vocabularyService, new BearerTokenService());
    }

    @Test
    void canCreateUser(){
        var user = new User();
        user.setName("foo");
        var createdUser = new User(1,user.getName());
        when(userService.create(user)).thenReturn(createdUser);

        var userOutput = userController.createUser(user);

        verify(userService).create(user);
        assertEquals(createdUser, userOutput);
    }

}