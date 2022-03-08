package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vocabularies")
public class VocabularyController {
    @Autowired
    private VocabularyService vocabularyService;

    @PostMapping
    public Vocabulary add(@RequestBody Vocabulary vocabulary){
        return vocabularyService.create(vocabulary);
    }

    @GetMapping("/greeting")
    public String greeting(){
        return vocabularyService.greet();
    }

    @GetMapping("/test")
    public String testing(@RequestParam Map<String,String> params){
        return params.get("favorite") +" "+ params.get("category");
    }

    @GetMapping
    public List<Vocabulary> getAllVocabularies(@RequestParam Map<String,String> params){
        List<Vocabulary> vocabularyList = vocabularyService.all();
        if(params.containsKey("favorite")){
            if(params.get("favorite").equals("true")){
                vocabularyList = vocabularyList.stream().filter(vocabulary -> vocabulary.isFavorite()).collect(Collectors.toList());
            }
        }

        return vocabularyList;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        vocabularyService.delete(id);
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
