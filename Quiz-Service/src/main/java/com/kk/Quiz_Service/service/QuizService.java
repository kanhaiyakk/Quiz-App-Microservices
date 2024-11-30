package com.kk.Quiz_Service.service;

import com.kk.Quiz_Service.entity.QuestionWrapper;
import com.kk.Quiz_Service.entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    ResponseEntity<String> createQuiz(String category, int numQ, String title);

    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

    ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses);
}

