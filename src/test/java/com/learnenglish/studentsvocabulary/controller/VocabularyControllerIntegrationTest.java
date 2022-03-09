package com.learnenglish.studentsvocabulary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnenglish.studentsvocabulary.infrastructure.controller.VocabularyController;
import com.learnenglish.studentsvocabulary.model.Vocabulary;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@ExtendWith(SpringExtension.class)
@WebMvcTest(VocabularyController.class)
class VocabularyControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private VocabularyService service;

    final Vocabulary RECORD_1 = new Vocabulary(1, "Rayven Yor","Cebu Philippines");
    final Vocabulary RECORD_2 = new Vocabulary(2, "David Landup", "New York USA");
    final Vocabulary RECORD_3 = new Vocabulary(3, "Jane Doe", "New York USA");

    @Test
    void shouldSayHello() throws Exception{
        when(service.greet()).thenReturn("Hello");
        this.mockMvc.perform(get("/vocabularies/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }

    @Test
    void getAllViaUrl() throws Exception{
        List<Vocabulary> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        when(service.all()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders.get("/vocabularies")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)))
                        .andExpect(jsonPath("$[2].name", is("Jane Doe")));
    }

    @Test
    void getOne() throws Exception{
        when(service.find(RECORD_1.getId())).thenReturn(RECORD_1);
        mockMvc.perform(get("/vocabularies/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Rayven Yor")));
    }

    /*
    @Test
    void createdOne() throws Exception{
        Vocabulary vocab = new Vocabulary();
        vocab.setName("john");
        vocab.setDefinition("blala");

        when(service.saveVocabulary(vocab)).thenReturn(vocab);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vocabularies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"john\",  \"definition\":\"mca\"}");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("john")));
    }
    */

    @Test
    public void updateOne() throws Exception{
        when(service.update(RECORD_1, RECORD_1.getId())).thenReturn(RECORD_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/vocabularies/"+RECORD_1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(RECORD_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is(RECORD_1.getName())));
    }

    @Test
    public void deletePatientById_success() throws Exception {
        //when(service.deleteVocabulary(RECORD_2.getId())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/vocabularies/"+RECORD_1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}