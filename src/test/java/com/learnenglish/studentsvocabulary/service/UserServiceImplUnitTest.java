package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class UserServiceImplUnitTest {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImplUnitTest.class);

    private UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void getUserById() {
        User user = new User(1,"foo");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        var newUser = userService.find(1);

        assertThat(user).isEqualTo(newUser);
        verify(userRepository).findById(1);
       // LOG.info(() -> user.getId()+Double.toString(Math.random()));
    }

    @Test
    void canAddVocabularies(){
        var vocabulary = new Vocabulary(1,"Hola", "Saludo");
        var user = spy(new User(1,"Foo"));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        userService.addVocabulary(user.getId(),vocabulary);

        verify(user).addVocabulary(vocabulary);
        verify(userRepository).save(user);
    }

}