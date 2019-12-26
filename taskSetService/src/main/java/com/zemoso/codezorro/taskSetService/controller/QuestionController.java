package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.model.Question;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.QuestionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionServiceInterface questionServiceInterface=null;

    //Create
    @PostMapping("/addQuestion")
    public Question createQuestion(@Valid @RequestBody Question question){
        return questionServiceInterface.addQuestion(question);
    }

    //Retrieve
    @GetMapping("/getQuestion/{questionId}")
    public Question getQuestionById(@PathVariable Long questionId) throws Exception {
        return questionServiceInterface.findQuestion(questionId).orElseThrow(Exception::new);
    }

    //Retrieve
    @GetMapping("/getAllQuestions")
    public List<Question> getAllQuestions(){
        return questionServiceInterface.findAll();
    }

    //Update
    @PutMapping("/updateQuestion/{questionId}")
    public Question updateQuestion(@PathVariable Long questionId, @Valid @RequestBody Question question) {
        return questionServiceInterface.updateQuestion(question);
    }

    //Delete
    @DeleteMapping("/deleteQuestionById/{questionId}")
    public void deleteQuestionById(@PathVariable Long questionId){
        questionServiceInterface.removeQuestion(questionId);
    }

    //Delete
    @DeleteMapping("/deleteQuestion/{questionId}")
    public void deleteQuestion(@PathVariable Long questionId, @Valid @RequestBody Question question){
        questionServiceInterface.removeQuestion(question);
    }
}
