package com.learnenglish.studentsvocabulary.controller;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.repository.VocabularyRepository;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ExtendWith(SpringExtension.class)
@WebMvcTest(VocabularyController.class)
class VocabularyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VocabularyService service;

    @Test
    void souldSayHello() throws Exception{
        when(service.greet()).thenReturn("Hello");
        this.mockMvc.perform(get("/vocabularies/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }


    @MockBean
    private VocabularyRepository vocabularyRepository;

    Vocabulary RECORD_1 = new Vocabulary(1, "Rayven Yor","Cebu Philippines");
    Vocabulary RECORD_2 = new Vocabulary(2, "David Landup", "New York USA");
    Vocabulary RECORD_3 = new Vocabulary(3, "Jane Doe", "New York USA");
    @Test
    void getAll_success() throws Exception{
        List<Vocabulary> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        Mockito.when(vocabularyRepository.findAll()).thenReturn(records);
    }
}