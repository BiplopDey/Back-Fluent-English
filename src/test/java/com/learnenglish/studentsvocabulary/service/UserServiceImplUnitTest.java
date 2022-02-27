package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
        var user = new User(1,"Foo");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        var newUser = userService.getUserById(1);

        assertThat(user).isEqualTo(newUser);
        verify(userRepository).findById(1);
       // LOG.info(() -> user.getId()+Double.toString(Math.random()));
    }

}