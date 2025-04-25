package com.quizapp.controllers;

import com.quizapp.models.Question;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    public QuestionService questionService;

    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestion();
        //return "Hi, These Are Your Questions";
    }

    @GetMapping("/category/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);

    }

    @PostMapping("/addquestion")
    public String addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
        return "Question Added Successfully";
    }
}
