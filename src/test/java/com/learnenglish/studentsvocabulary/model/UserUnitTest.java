package com.learnenglish.studentsvocabulary.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserUnitTest {
    @Test
    void testUserWithSameIdAreEquals(){
        var user1 = new User(1,"foo");
        var user2 = new User(1,"bazz");
        assertEquals(user1, user2);
    }
}