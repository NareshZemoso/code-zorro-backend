package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.model.Question;
import com.zemoso.codezorro.taskSetService.model.TestCase;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.QuestionServiceInterface;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestCaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionServiceInterface questionServiceInterface=null;

    @Autowired
    private TestCaseServiceInterface testCaseServiceInterface=null;

    @GetMapping("/")
    public String respond()
    {
        return "Hello";
    }

    //Create
    @PostMapping("/addQuestion")
    public Question createQuestion(@Valid @RequestBody Question question){
        return questionServiceInterface.addQuestion(question);
    }

    //Add testcases to a question
    @PostMapping("/{questionId}/addTestCaseToQuestion")
    public Question addTestCaseToQuestion(@PathVariable (value = "questionId")Long questionId,
                                  @Valid @RequestBody TestCase testCase) throws Exception {
        Question question = questionServiceInterface.findQuestion(questionId).orElseThrow(Exception::new);
        Set<TestCase> set = question.getTestCases();
        set.add(testCase);
        question.setTestCases(set);
        questionServiceInterface.addQuestion(question);
        return question;
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

    //Retrieve all the testcases in a question
    @GetMapping("{questionId}/allTestCases")
    public List<TestCase> getAllTestCasesFromQuestion(@PathVariable (value="questionId") Long questionId) throws Exception {
        Question question= questionServiceInterface.findQuestion(questionId).orElseThrow(Exception::new);
        return new ArrayList<>(question.getTestCases());
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

    //Delete a particular testcase from the question
    @DeleteMapping("/{questionId}/deleteTestCase/{testCaseId}")
    public void deleteTestCaseFromQuestion(@PathVariable Long questionId, @PathVariable Long testCaseId){
        questionServiceInterface.removeTestCaseFromQuestion(questionId,testCaseId);
    }
}
