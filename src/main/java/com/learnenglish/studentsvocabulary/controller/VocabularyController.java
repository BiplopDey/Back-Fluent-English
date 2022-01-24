package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
