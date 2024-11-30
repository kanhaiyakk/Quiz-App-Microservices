package com.kk.Question_Service.service;

import com.kk.Question_Service.entity.Question;
import com.kk.Question_Service.entity.QuestionWrapper;
import com.kk.Question_Service.entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
    List<Question> getQuestionsByCategory(String category);
    Question getQuestionsById(Integer id);
    Question addQuestion(Question question);
    Question updateQuestionDetails(Integer id ,Question question);
    void deleteQuestion(Integer id);

    ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions);

    ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds);

    ResponseEntity<Integer> getScore(List<Response> responses);
}

