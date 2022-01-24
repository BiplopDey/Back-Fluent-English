package com.learnenglish.studentsvocabulary.repository;

import com.learnenglish.studentsvocabulary.model.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Integer> {
}
