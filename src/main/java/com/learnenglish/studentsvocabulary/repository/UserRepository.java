package com.learnenglish.studentsvocabulary.repository;

import com.learnenglish.studentsvocabulary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
