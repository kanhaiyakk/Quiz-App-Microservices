package com.kk.Question_Service.serviceImpl;

import com.kk.Question_Service.entity.Question;
import com.kk.Question_Service.entity.QuestionWrapper;
import com.kk.Question_Service.entity.Response;
import com.kk.Question_Service.exceptions.ResourceNotFoundException;
import com.kk.Question_Service.repository.QuestionRepo;
import com.kk.Question_Service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    @Override
    public Question getQuestionsById(Integer id) {
        Optional<Question> question= questionRepo.findById(id);
        if (question.isPresent()){
            return question.get();
        }
        throw new ResourceNotFoundException("Question not found for the id "+ id);
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public Question updateQuestionDetails(Integer id, Question question) {
        System.out.println("shashi");
        Question existingQuestion = this.questionRepo.findById(id).orElseThrow((()-> new ResourceNotFoundException("this is i not availve"+ id)));
        existingQuestion.setQuestionTitle(question.getQuestionTitle() != null ? question.getQuestionTitle() : existingQuestion.getQuestionTitle());
        existingQuestion.setOption1(question.getOption1() != null ? question.getOption1() : existingQuestion.getOption1());
        existingQuestion.setOption2(question.getOption2() != null ? question.getOption2() : existingQuestion.getOption2());
        existingQuestion.setOption3(question.getOption3() != null ? question.getOption3() : existingQuestion.getOption3());
        existingQuestion.setOption4(question.getOption4() != null ? question.getOption4() : existingQuestion.getOption4());
        existingQuestion.setRightAnswer(question.getRightAnswer() != null ? question.getRightAnswer() : existingQuestion.getRightAnswer());
        existingQuestion.setDifficultyLevel(question.getDifficultyLevel() != null ? question.getDifficultyLevel() : existingQuestion.getDifficultyLevel());
        existingQuestion.setCategory(question.getCategory() != null ? question.getCategory() : existingQuestion.getCategory());

        return questionRepo.save(existingQuestion);
    }



    @Override
    public void deleteQuestion(Integer id) {

        questionRepo.deleteById(id);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions){
        List<Integer> questions = questionRepo.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public  ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds){
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id : questionIds){
            questions.add(questionRepo.findById(id).get());
        }

        for (Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());

            wrappers.add(wrapper);
        }


        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public  ResponseEntity<Integer> getScore(List<Response> responses){


        int right=0;

        for(Response response : responses){
            Question question = questionRepo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }


        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}

