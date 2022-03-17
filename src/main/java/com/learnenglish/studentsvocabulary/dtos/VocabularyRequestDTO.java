package com.learnenglish.studentsvocabulary.dtos;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VocabularyRequestDTO {
    private String name;
    private String definition;
    private Boolean favorite;
    private Boolean phrasalVerb;

    public VocabularyRequestDTO(String name, String definition, Boolean favorite, Boolean phrasalVerb) {
        this.name = name;
        this.definition = definition;
        this.favorite = favorite == null ? false : favorite;
        this.phrasalVerb = phrasalVerb == null ? false : phrasalVerb;
    }

    Vocabulary toVocabulary(){
        // checking
        var vocab = new Vocabulary();
        return vocab;
    }
}
