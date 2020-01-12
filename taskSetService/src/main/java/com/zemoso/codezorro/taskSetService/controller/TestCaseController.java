package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.model.TestCase;
import com.zemoso.codezorro.taskSetService.repository.QuestionRepo;
import com.zemoso.codezorro.taskSetService.repository.TestCaseRepo;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.QuestionServiceInterface;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestCaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/question/testCase")
public class TestCaseController {

    @Autowired
    private QuestionServiceInterface questionServiceInterface=null;

    @Autowired
    private TestCaseServiceInterface testCaseServiceInterface=null;

    @Autowired
    private QuestionRepo questionRepo=null;

    @Autowired
    private TestCaseRepo testCaseRepo=null;

    //Create
    @PostMapping("/{questionId}/addTestCase")
    public TestCase createTestCase(@PathVariable (value = "questionId") Long questionId,
                                   @Valid @RequestBody TestCase testCase ) throws Exception {
        return questionServiceInterface.findQuestion(questionId).map(question -> {
            testCase.setQuestion(question);
            return testCaseServiceInterface.addTestCase(testCase);
        }).orElseThrow(Exception::new);
    }

    @GetMapping("/{questionId}/getTestCase/{testCaseId}")
    public TestCase getTestCaseById(@PathVariable(value = "questionId") Long questionId,
                                    @PathVariable(value = "testCaseId") Long testCaseId) throws Exception {
        return questionServiceInterface.findQuestion(questionId).map(
                question -> testCaseServiceInterface.findTestCase(testCaseId).get()).orElseThrow(Exception::new);
    }

    //Retrieve
    @GetMapping("/{questionId}/getAllTestCases")
    public List<TestCase> getAllTestCasesByQuestionId(@PathVariable (value = "questionId") Long questionId,
                                                      Pageable pageable){
        return testCaseRepo.findByQuestionId(questionId,pageable);
    }

    //Update
    @PutMapping("/updateTestCase/{testCaseId}")
    public TestCase updateTestCase(@PathVariable(value = "questionId") Long questionId,
                                   @PathVariable(value = "testCaseId") Long testCaseId,
                                   @Valid @RequestBody TestCase testCase) throws Exception {
        //return testCaseServiceInterface.updateTestCase(testCase);
        if(!questionRepo.existsById(questionId)){
            throw new Exception();
        }

        return testCaseServiceInterface.findTestCase(testCaseId).map(testCase1 -> {
           testCase1.setInput("1 2 3 4 5 6");
           testCase1.setOutput("21");
           testCase1.setWeightage(0);
           return testCaseServiceInterface.addTestCase(testCase1);
        }).orElseThrow(Exception::new);
    }

    //Delete
    @DeleteMapping("/{questionId}/deleteTestCaseById/{testCaseId}")
    public ResponseEntity<?> deleteTestCaseById(@PathVariable(value = "questionId") Long questionId,
                                                @PathVariable(value = "testCaseId") Long testCaseId) throws Exception {
        return testCaseRepo.findByIdAndQuestionId(testCaseId, questionId).map(testCase -> {
            testCaseServiceInterface.removeTestCase(testCaseId);
            return ResponseEntity.ok().build();
        }).orElseThrow(Exception::new);
    }
}
