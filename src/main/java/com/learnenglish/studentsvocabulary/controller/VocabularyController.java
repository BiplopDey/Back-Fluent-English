package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Predicate<Vocabulary>> vocabularyPredicateList = new ArrayList<>();
        params.forEach((key,value)->{
            if(key.equals("favorite") && value.equals("true")) vocabularyPredicateList.add(v-> v.isFavorite());
            if(key.equals("category") && value.equals("phrasal-verb")) vocabularyPredicateList.add(v-> v.isPhrasalVerb());
        });

        Predicate<Vocabulary> intersectQuerys = vocabularyPredicateList.stream().reduce(v->true, Predicate::and);
        return vocabularyList.stream().filter(intersectQuerys).collect(Collectors.toList());
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
