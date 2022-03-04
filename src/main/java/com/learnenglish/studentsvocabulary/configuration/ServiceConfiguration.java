package com.learnenglish.studentsvocabulary.configuration;

import com.learnenglish.studentsvocabulary.repository.UserRepository;
import com.learnenglish.studentsvocabulary.repository.VocabularyRepository;
import com.learnenglish.studentsvocabulary.service.UserService;
import com.learnenglish.studentsvocabulary.service.UserServiceImpl;
import com.learnenglish.studentsvocabulary.service.VocabularyService;
import com.learnenglish.studentsvocabulary.service.VocabularyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public UserService getUserService(UserRepository userRepository){
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public VocabularyService getVocabularyService(VocabularyRepository vocabularyRepository){
        return new VocabularyServiceImpl(vocabularyRepository);
    }
}
