package com.learnenglish.studentsvocabulary.service;

import com.learnenglish.studentsvocabulary.controller.VocabularyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VocabularyServiceImplIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void greet() throws Exception{
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/vocabularies/greeting",
                String.class)).contains("Hello");
    }

    @Test
    void vocabularies() throws Exception{
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/vocabularies",
                String.class)).contains("que");
    }

}