package com.learnenglish.studentsvocabulary.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserUnitTest {
    @Test
    void testUserWithSameAtributtesAreEquals(){
        var user1 = new User(1,"foo");
        var user2 = new User(1,"foo");
        assertEquals(user1, user2);
    }
}