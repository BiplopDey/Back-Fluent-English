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
    User user;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
        user = new User("foo","foo");
        user.setId(1L);
    }

    @Test
    void getUserById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        var newUser = userService.find(user.getId());

        assertThat(user).isEqualTo(newUser);
        verify(userRepository).findById(user.getId());

       // LOG.info(() -> user.getId()+Double.toString(Math.random()));
    }

    @Test
    void canAddVocabularies(){
        var vocabulary = new Vocabulary("Hola", "Saludo");
        var user = spy(new User("Foo","Foo"));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.addVocabulary(user.getId(), vocabulary);

        verify(user).addVocabulary(vocabulary);
        verify(userRepository).save(user);
    }

}