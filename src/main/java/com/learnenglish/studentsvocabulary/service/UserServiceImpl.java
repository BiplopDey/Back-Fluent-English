package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User find(int id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }
}
