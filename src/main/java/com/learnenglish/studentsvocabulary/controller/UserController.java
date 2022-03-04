package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.service.TokenService;
import com.learnenglish.studentsvocabulary.service.UserService;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final VocabularyService vocabularyService;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, VocabularyService vocabularyService, TokenService tokenService) {
        this.userService = userService;
        this.vocabularyService = vocabularyService;
        this.tokenService = tokenService;
    }

    @GetMapping("/token")
    public Map<String, String> readAndReturnToken(
            @RequestHeader(value="Authorization") String bearToken){
        HashMap<String, String> map = new HashMap<>();
        map.put("accessToken",bearToken.split("\\s+")[1]);
        return map;
    }

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user){
        userService.create(user);
        HashMap<String, String> map = new HashMap<>();
        String token=tokenService.generate(user);
        map.put("accessToken", token);
        return map;
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
    public Set<Vocabulary> vocabularies(@PathVariable int id){
        return userService.find(id).getVocabularies();
    }

    @PostMapping("/{id}/vocabularies")
    public Vocabulary addVocabulary(@PathVariable int id, @RequestBody Vocabulary vocabulary){
        var addedVocabulary = vocabularyService.create(vocabulary);
        userService.addVocabulary(id,addedVocabulary);
        return addedVocabulary;
    }

    @PutMapping("/{userId}/vocabularies/{vocabId}")
    public Vocabulary updateVocabulary(@PathVariable("userId") int userId,
                                       @PathVariable("vocabId") int vocabId,
                                       @RequestBody Vocabulary vocabulary){
        var updatedVocab = vocabularyService.update(vocabulary, vocabId);
        return updatedVocab;
    }

    @DeleteMapping("/{userId}/vocabularies/{vocabId}")
    public void detach(@PathVariable("userId") int userId,
                        @PathVariable("vocabId") int vocabId){
        var vocabToDetach = vocabularyService.find(vocabId);
        userService.detachVocabulary(userId, vocabToDetach);
    }
}
