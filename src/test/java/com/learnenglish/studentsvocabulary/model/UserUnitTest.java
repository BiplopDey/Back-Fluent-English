package com.learnenglish.studentsvocabulary.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserUnitTest {
    @Test
    void testUserWithSameIdAreEquals(){
        var user1 = new User("bazz","foo");
        var user2 = new User("bazz1","bazz");
        user1.setId(2L);
        user2.setId(2L);

        assertEquals(user1, user2);
        assertTrue(user1.equals(user2));
    }

    @Test
    void testUserSet(){
        var user1 = new User("bazz","foo");
        var user2 = new User("bazz1","bazz");
        user1.setId(2L);
        user2.setId(2L);

        Set<User> userSet = new HashSet<>();
        userSet.add(user1);
        userSet.add(user2);
        assertEquals(userSet.size(), 1);
    }
}