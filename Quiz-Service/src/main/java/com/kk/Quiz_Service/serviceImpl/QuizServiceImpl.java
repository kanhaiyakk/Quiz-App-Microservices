package com.kk.Quiz_Service.serviceImpl;

import com.kk.Quiz_Service.entity.QuestionWrapper;
import com.kk.Quiz_Service.entity.Quiz;
import com.kk.Quiz_Service.entity.Response;
import com.kk.Quiz_Service.feign.QuizInterface;
import com.kk.Quiz_Service.repository.QuizRepo;
import com.kk.Quiz_Service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title){

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);



    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
        Quiz quiz=quizRepo.findById(id).get();

        List<Integer> questionsIds= quiz.getQuestionsIds();

        ResponseEntity<List<QuestionWrapper>> questions= quizInterface.getQuestionsFromId(questionsIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses){

        ResponseEntity<Integer> score= quizInterface.getScore(responses);
        return score;


    }
}

