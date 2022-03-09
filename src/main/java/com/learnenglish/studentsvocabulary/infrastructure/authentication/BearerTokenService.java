package com.learnenglish.studentsvocabulary.infrastructure.authentication;

import com.learnenglish.studentsvocabulary.model.User;

import java.util.HashMap;
import java.util.Map;

public class BearerTokenService implements TokenService{
    private static Map<String,User> tokens = new HashMap<>();

    @Override
    public String generate(User user) {
        String randomToken = getAlphaNumericString(20);
        tokens.put(randomToken, user);
        return randomToken;
    }

    private String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    @Override
    public boolean exists(String token) {
        return tokens.containsKey(token);
    }

    @Override
    public User getUser(String token) {
        return tokens.get(token);
    }
}
