package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.model.User;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.repository.UserRepository;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VocabularyService vocabularyService;

    @PostMapping
    public User add(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User one(@PathVariable int id){
        var user = userRepository.findById(id);
        if(!user.isPresent()) return null;
        return user.get();
    }

    @GetMapping("/{id}/vocabularies")
    public Set<Vocabulary> vocabularies(@PathVariable int id){
        var userToFind = userRepository.findById(id);
        if(!userToFind.isPresent()) return null;
        return userToFind.get().getVocabularies();
    }

    @PostMapping("/{id}/vocabularies")
    public Vocabulary addVocabulary(@PathVariable int id, @RequestBody Vocabulary vocabulary){
        var userToFind = userRepository.findById(id);
        if(!userToFind.isPresent()) return null;
        var user = userToFind.get();

        var addedVocabulary = vocabularyService.saveVocabulary(vocabulary);

        var newVocab = user.getVocabularies();
        newVocab.add(addedVocabulary);
        user.setVocabularies(newVocab);
        userRepository.save(user);
        return addedVocabulary;
    }

    @PutMapping("/{userId}/vocabularies/{vocabId}")
    public Vocabulary updateVocabulary(@PathVariable("userId") int userId,
                                       @PathVariable("vocabId") int vocabId,
                                       @RequestBody Vocabulary vocabulary){

        var userToFind = userRepository.findById(userId);
        if(!userToFind.isPresent()) return null;
        var user = userToFind.get();

        var addedVocabulary = vocabularyService.update(vocabulary, vocabId);

        var newVocab = user.getVocabularies();
        newVocab.add(addedVocabulary);
        user.setVocabularies(newVocab);
        userRepository.save(user);
        return addedVocabulary;
    }


    @DeleteMapping("/{userId}/vocabularies/{vocabId}")
    public void delete(@PathVariable("userId") int userId,
                       @PathVariable("vocabId") int vocabId){

        var userToFind = userRepository.findById(userId);
        if(!userToFind.isPresent()) return;
        var user = userToFind.get();
        var deletedVocab = vocabularyService.find(vocabId);

        user.removeVocabulary(deletedVocab);

        //vocabularyService.deleteVocabulary(vocabId);

        /*
        var newVocab = user.getVocabularies();
        newVocab.remove(deletedVocab);
        user.setVocabularies(newVocab);
        userRepository.save(user);
         */
    }

}
