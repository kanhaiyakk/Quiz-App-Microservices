package com.kk.Question_Service.controller;


import com.kk.Question_Service.entity.Question;
import com.kk.Question_Service.entity.QuestionWrapper;
import com.kk.Question_Service.entity.Response;
import com.kk.Question_Service.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return new ResponseEntity<>( questionService.getQuestionsByCategory(category),HttpStatus.OK);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getQuestionsById(@PathVariable Integer id){
        return new ResponseEntity<>(questionService.getQuestionsById(id),HttpStatus.OK);
    }

    @PostMapping("/question")
    public ResponseEntity<Question> addQuestion(@Valid @RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question),HttpStatus.CREATED);
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<Question> updateQuestionDetails(@RequestBody Question question , @PathVariable Integer id) {
        Question updatedQuestion = questionService.updateQuestionDetails(id, question);
        return new ResponseEntity<>(question,HttpStatus.OK);
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete")
    private void deleteQuestion(@RequestParam Integer id){
        questionService.deleteQuestion(id);
    }



    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}
