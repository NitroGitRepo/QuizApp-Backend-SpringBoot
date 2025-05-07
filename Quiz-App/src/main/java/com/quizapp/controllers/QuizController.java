package com.quizapp.controllers;

import com.quizapp.models.Question;
import com.quizapp.models.QuestionQrapper;
import com.quizapp.models.Response;
import com.quizapp.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    public QuizService quizService;

    @GetMapping("/status")
    public String checkStatus(){
          return "Quiz Controller";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numberQ, @RequestParam String title){
        System.out.println("Quiz Controller Called");
        return quizService.createQuiz(category,numberQ, title);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionQrapper>> getQuizQuestions(@PathVariable Integer id){
          return quizService.getQuizQuestion(id);
    }

    @PostMapping("/submit/{id}")
    public  ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses1){
          return  quizService.calculateResult(id, responses1);
    }



}
