package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vocabulary")
public class VocabularyController {
    @Autowired
    private VocabularyService vocabularyService;

    @PostMapping("/add")
    public String add(@RequestBody Vocabulary vocabulary){
        vocabularyService.saveVocabulary(vocabulary);
        return "New vocabulary is added";
    }

    @GetMapping("/getAll")
    public List<Vocabulary> getAllVocabularies(){
        return vocabularyService.getAllVocabularies();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        vocabularyService.deleteVocabulary(id);
    }

    @GetMapping("/{id}")
    public Vocabulary one(@PathVariable int id){
        return vocabularyService.find(id);
    }

    @PutMapping("update/{id}")
    public Vocabulary update(@RequestBody Vocabulary vocabulary, @PathVariable int id){
        return vocabularyService.update(vocabulary, id);
    }

}
