package com.learnenglish.studentsvocabulary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnenglish.studentsvocabulary.infrastructure.controller.VocabularyController;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VocabularyController.class)
class VocabularyControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private VocabularyService service;

    Vocabulary RECORD_1,RECORD_2,RECORD_3;
    List<Vocabulary> records;

    @BeforeEach
    void setUp(){
        RECORD_1 = new Vocabulary(1, "Rayven Yor","Cebu Philippines");
        RECORD_2 = new Vocabulary(2, "David Landup", "New York USA");
        RECORD_3 = new Vocabulary(3, "Jane Doe", "New York USA");
        records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
    }

    @Test
    void shouldSayHello() throws Exception {
        when(service.greet()).thenReturn("Hello");

        mockMvc.perform(get("/vocabularies/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }

    @Test
    void getAllViaUrl() throws Exception{
        when(service.all()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders.get("/vocabularies")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)))
                        .andExpect(jsonPath("$[2].name", is("Jane Doe")));
    }

    @Test
    void getOne() throws Exception{
        final int id = RECORD_1.getId();
        when(service.find(id)).thenReturn(RECORD_1);

        mockMvc.perform(get("/vocabularies/"+id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Rayven Yor")));
    }

    @Test
    void createdOne() throws Exception{
        when(service.create(ArgumentMatchers.any(Vocabulary.class))).thenReturn(RECORD_1);

        mockMvc.perform(post("/vocabularies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(RECORD_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(RECORD_1.getName())));

        verify(service).create(RECORD_1);
    }

    @Test
    public void updateOne() throws Exception{
        final int id = RECORD_1.getId();
        when(service.update(RECORD_1, id)).thenReturn(RECORD_1);

        mockMvc.perform(put("/vocabularies/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(RECORD_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(RECORD_1.getName())));
    }

    @Test
    public void deleteVocabularyById() throws Exception {
        final int id = RECORD_1.getId();

        mockMvc.perform(delete("/vocabularies/"+id))
                .andExpect(status().isOk());
        verify(service).delete(id);
    }
}