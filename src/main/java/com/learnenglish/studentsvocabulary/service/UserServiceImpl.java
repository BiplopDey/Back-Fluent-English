package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User find(Long id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addVocabulary(Long id, Vocabulary vocabulary) {
        var user = find(id);
        user.addVocabulary(vocabulary);
        userRepository.save(user);
    }

    @Override
    public void detachVocabulary(Long id, Vocabulary vocabulary) {
        var user = find(id);
        user.removeVocabulary(vocabulary);
        userRepository.save(user);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Set<Vocabulary> getVocabularies(Long id) {
        return find(id).getVocabularies();
    }
}
