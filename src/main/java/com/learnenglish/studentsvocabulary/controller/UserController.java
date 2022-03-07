package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.dtos.LoginUserResponseDto;
import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.service.LogInService;
import com.learnenglish.studentsvocabulary.service.UserService;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final VocabularyService vocabularyService;
    private final LogInService logInService;
    @Autowired
    public UserController(UserService userService, VocabularyService vocabularyService,  LogInService logInService) {
        this.userService = userService;
        this.vocabularyService = vocabularyService;
        this.logInService = logInService;
    }

    @PostMapping("/register")
    public LoginUserResponseDto registerUser(@RequestBody User user){
        User registeredUser = userService.create(user);
        return logInService.logIn(registeredUser);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return userService.find(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.delete(id);
    }

    @GetMapping("/{id}/vocabularies")
    public Set<Vocabulary> vocabularies(@PathVariable int id,
                                        @RequestHeader(value="Authorization") String bearerToken){
        if(!logInService.isLogedIn(id, bearerToken))
            return null;

        return userService.getVocabularies(id);
    }

    @PostMapping("/{id}/vocabularies")
    public Vocabulary addVocabulary(@PathVariable int id,
                                    @RequestBody Vocabulary vocabulary,
                                    @RequestHeader(value="Authorization") String bearerToken){
        if(!logInService.isLogedIn(id, bearerToken))
            return null;

        var createdVocab = vocabularyService.create(vocabulary);
        userService.addVocabulary(id, createdVocab);
        return createdVocab;
    }

    @PutMapping("/{userId}/vocabularies/{vocabId}")
    public Vocabulary updateVocabulary(@PathVariable("userId") int userId,
                                       @PathVariable("vocabId") int vocabId,
                                       @RequestBody Vocabulary vocabulary,
                                       @RequestHeader(value="Authorization") String bearerToken){
        if(!logInService.isLogedIn(userId, bearerToken))
            return null;

        var updatedVocab = vocabularyService.update(vocabulary, vocabId);
        return updatedVocab;
    }

    @DeleteMapping("/{userId}/vocabularies/{vocabId}")
    public void detach(@PathVariable("userId") int userId,
                       @PathVariable("vocabId") int vocabId,
                       @RequestHeader(value="Authorization") String bearerToken){
        if(!logInService.isLogedIn(userId, bearerToken))
            return;

        var vocabToDetach = vocabularyService.find(vocabId);
        userService.detachVocabulary(userId, vocabToDetach);
    }
}
