package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vocabularies")
public class VocabularyController {
    @Autowired
    private VocabularyService vocabularyService;

    @PostMapping
    public Vocabulary add(@RequestBody Vocabulary vocabulary){
        return vocabularyService.saveVocabulary(vocabulary);
    }

    @GetMapping("/greeting")
    public String greeting(){
        return vocabularyService.greet();
    }

    @GetMapping
    public List<Vocabulary> getAllVocabularies(){
        return vocabularyService.getAllVocabularies();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        vocabularyService.deleteVocabulary(id);
    }

    @GetMapping("/{id}")
    public Vocabulary one(@PathVariable int id){
        return vocabularyService.find(id);
    }

    @PutMapping("/{id}")
    public Vocabulary update(@RequestBody Vocabulary vocabulary, @PathVariable int id){
        return vocabularyService.update(vocabulary, id);
    }

}
