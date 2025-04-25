package com.quizapp.service;

import com.quizapp.models.Question;
import com.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    public QuestionRepository questionRepository;


    public List<Question> getAllQuestion(){
       return questionRepository.findAll();
    }

    public List<Question> getQuestionByCategory(String category){
        return questionRepository.findByCategory(category);
    }

    public void addQuestion(Question question){
        questionRepository.save(question);
    }
}
