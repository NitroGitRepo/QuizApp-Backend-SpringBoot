package com.quizapp.service;

import com.quizapp.models.Question;
import com.quizapp.models.QuestionQrapper;
import com.quizapp.models.Quiz;
import com.quizapp.models.Response;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
   public QuizRepository quizRepository;

    @Autowired
    public QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title){
        System.out.println("Create Quiz Service");
      try{
          List<Question> questions = questionRepository.findLimitedQuestionsByCategory(category, numQ);
          Quiz q = new Quiz();
          q.setTitle(title);
          q.setQuestions(questions);
          quizRepository.save(q);
      }catch (Exception e){
          System.out.println(e);
          return new ResponseEntity<>("Failed To Fetch", HttpStatus.BAD_REQUEST);
      }
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionQrapper>> getQuizQuestion(int id){
        Optional<Quiz> quiz = quizRepository.findById(id);

        List<Question> questionFromDB = quiz.get().getQuestions();

        List<QuestionQrapper> questionQrappers = new ArrayList<>();

        for(Question q: questionFromDB){
            QuestionQrapper q1 = new QuestionQrapper();
            q1.setId(q.getId());
            q1.setQuestionTitle(q.getQuestionTitle());
            q1.setOption1(q.getOption1());
            q1.setOption2(q.getOption2());
            q1.setOption3(q.getOption3());
            questionQrappers.add(q1);
        }
        return  new ResponseEntity<>(questionQrappers, HttpStatus.OK);

    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses){
        Optional<Quiz> quiz = quizRepository.findById(id);

        int score = 0;
        List<Question> questionFromDB = quiz.get().getQuestions();
        int i=0;
        for(Response response: responses){
            System.out.println(response.getResponse() + "   " +questionFromDB.get(i).getWriteAnswer());
             if(response.getResponse().equals(questionFromDB.get(i).getWriteAnswer()))
             {

                 score++;
             }
             i++;
        }

        return  new ResponseEntity<>(score, HttpStatus.OK);
    }
}
