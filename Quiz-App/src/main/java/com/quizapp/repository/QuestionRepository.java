package com.quizapp.repository;

import com.quizapp.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Override
    List<Question> findAll();


    List<Question> findByCategory(String category);

//    @Query(value = "SELECT * FROM question_table q where q.category:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
//    List<Question> findRandomQuestionByCategory(String category, int numQ);

    @Query(value = "SELECT * FROM question q WHERE q.category = :category LIMIT :numQ", nativeQuery = true)
    List<Question> findLimitedQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);



}
