package com.quizapp.repository;

import com.quizapp.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Override
    List<Question> findAll();


    List<Question> findByCategory(String category);
}
