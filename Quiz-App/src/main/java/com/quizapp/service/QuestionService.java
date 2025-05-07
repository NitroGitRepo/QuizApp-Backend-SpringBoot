package com.quizapp.service;

import com.quizapp.models.Question;
import com.quizapp.models.QuestionQrapper;
import com.quizapp.models.Quiz;
import com.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    public QuestionRepository questionRepository;


    //fetch all question
    public List<Question> getAllQuestion(){
       return questionRepository.findAll();
    }

    //fetch question based on category
    public ResponseEntity<List<Question>> getQuestionByCategory(String category){
        return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK) ;
    }


    //add a question
    public ResponseEntity<String> addQuestion(Question question){
        try {
            questionRepository.save(question);
        }catch (Exception e){
            return new ResponseEntity<>("Unable To Add Question", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("success", HttpStatus.CREATED);
    }


}
