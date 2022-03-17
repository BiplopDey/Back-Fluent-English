package com.learnenglish.studentsvocabulary.infrastructure.controller;

import com.learnenglish.studentsvocabulary.dtos.CreateUserRequestDTO;
import com.learnenglish.studentsvocabulary.dtos.LoginUserResponseDTO;
import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.infrastructure.authentication.LogInService;
import com.learnenglish.studentsvocabulary.service.UserService;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    public LoginUserResponseDTO registerUser(@RequestBody CreateUserRequestDTO createUserRequest){
        User registeredUser = userService.create(createUserRequest.getUser());
        return logInService.logIn(registeredUser);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.find(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id,
                           @RequestHeader(value="Authorization") String bearerToken){
        if(!logInService.isLogedIn(id, bearerToken))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing Authorization header");

        userService.delete(id);
    }

    @GetMapping("/{id}/vocabularies")
    public List<Vocabulary> vocabularies(@PathVariable Long id,
                                        @RequestHeader(value="Authorization") String bearerToken,
                                        @RequestParam Map<String,String> params){
        if(!logInService.isLogedIn(id, bearerToken))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing Authorization header");

        List<Predicate<Vocabulary>> vocabularyPredicateList = new ArrayList<>();
        params.forEach((key,value)->{
            if(key.equals("favorite") && value.equals("true")) vocabularyPredicateList.add(v-> v.isFavorite());
            if(key.equals("category") && value.equals("phrasal-verb")) vocabularyPredicateList.add(v-> v.isPhrasalVerb());
        });

        Predicate<Vocabulary> intersectQuerys = vocabularyPredicateList.stream().reduce(v->true, Predicate::and);
        return userService.getVocabularies(id).stream().filter(intersectQuerys).collect(Collectors.toList());
    }

    @PostMapping("/{id}/vocabularies")
    public Vocabulary addVocabulary(@PathVariable Long id,
                                    @RequestBody Vocabulary vocabulary,
                                    @RequestHeader(value="Authorization") String bearerToken){
        if(!logInService.isLogedIn(id, bearerToken))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing Authorization header");

        var createdVocab = vocabularyService.create(vocabulary);
        userService.addVocabulary(id, createdVocab);
        return createdVocab;
    }

    @PutMapping("/{userId}/vocabularies/{vocabId}")
    public Vocabulary updateVocabulary(@PathVariable("userId") Long userId,
                                       @PathVariable("vocabId") Long vocabId,
                                       @RequestBody Vocabulary vocabulary,
                                       @RequestHeader(value="Authorization") String bearerToken){
        if(!logInService.isLogedIn(userId, bearerToken))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing Authorization header");

        return vocabularyService.update(vocabulary, vocabId);
    }

    @DeleteMapping("/{userId}/vocabularies/{vocabId}")
    public void detach(@PathVariable("userId") Long userId,
                       @PathVariable("vocabId") Long vocabId,
                       @RequestHeader(value="Authorization") String bearerToken){
        if(!logInService.isLogedIn(userId, bearerToken))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing Authorization header");

        var vocabToDetach = vocabularyService.find(vocabId);
        userService.detachVocabulary(userId, vocabToDetach);
    }
}
