package com.learnenglish.studentsvocabulary.infrastructure.configuration;

import com.learnenglish.studentsvocabulary.infrastructure.authentication.BearerTokenService;
import com.learnenglish.studentsvocabulary.infrastructure.authentication.LogInService;
import com.learnenglish.studentsvocabulary.infrastructure.authentication.LogInServiceImp;
import com.learnenglish.studentsvocabulary.infrastructure.authentication.TokenService;
import com.learnenglish.studentsvocabulary.repository.UserRepository;
import com.learnenglish.studentsvocabulary.repository.VocabularyRepository;
import com.learnenglish.studentsvocabulary.service.*;
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

    @Bean
    public TokenService getTokenService(){
        return new BearerTokenService();
    }

    @Bean
    public LogInService getLogInService(TokenService tokenService){
        return new LogInServiceImp(tokenService);
    }
}
