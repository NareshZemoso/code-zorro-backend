package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.controller.dto.Message;
import com.zemoso.codezorro.taskSetService.model.Question;
import com.zemoso.codezorro.taskSetService.model.Test;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.QuestionServiceInterface;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestServiceInterface testServiceInterface=null;

    @Autowired
    private QuestionServiceInterface questionServiceInterface=null;

    //Create a test
    @PostMapping("/addTest")
    public Test createTest(@Valid @RequestBody Test test){
        return testServiceInterface.addTest(test);
    }

    //Add Questions to a test
    @PostMapping("/{testId}/addQuestion/{questionId}")
    public Test addQuestionToTest(@PathVariable (value = "testId")Long testId,
                                  @PathVariable (value = "questionId")Long questionId) throws Exception {
        Test test = testServiceInterface.findTest(testId).orElseThrow(Exception::new);
        Question question = questionServiceInterface.findQuestion(questionId).orElseThrow(Exception::new);
        Set<Question> set = test.getQuestions();
        set.add(question);
        test.setQuestions(set);
        testServiceInterface.addTest(test);
        return test;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/validate/{testLink}/{accessKey}")
    public ResponseEntity<Message> validate(@PathVariable("testLink")String testLink,
                                            @PathVariable("accessKey")String accessKey)
    {
        Message message = new Message();
        if(testServiceInterface.validate(testLink,accessKey))
        {
            message.setSuccess(true);
            message.setData("Link Validated Successfully");
        }
        else
        {
            message.setSuccess(false);
            message.setReason("Link Validation Failed");
        }
        return ResponseEntity.ok(message);
    }

    //Retrieve a single test by ID
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getTest/{testId}")
    public Test getTestById(@PathVariable Long testId) throws Exception {
        return testServiceInterface.findTest(testId).orElseThrow(Exception::new);
    }

    //Retrieve all the tests
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllTests")
    public List<Test> getAllTests(){
        return testServiceInterface.findAll();
    }

    //Retrieve all the questions in a test
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{testId}/allQuestions")
    public List<Question> getAllQuestionsOfTest(@PathVariable (value="testId") Long testId) throws Exception {
        Test test= testServiceInterface.findTest(testId).orElseThrow(Exception::new);
        return new ArrayList<>(test.getQuestions());
    }

    //Update a single test
    @PutMapping("/updateTest/{testId}")
    public Test updateTest(@PathVariable Long testId, @Valid @RequestBody Test test) {
        return testServiceInterface.updateTest(test);
    }

    //Delete a test by ID
    @DeleteMapping("/deleteTestById/{testId}")
    public void deleteTestById(@PathVariable Long testId){
        testServiceInterface.removeTest(testId);
    }

    @DeleteMapping("/deleteAllTests")
    public void deleteAllTests(){
        testServiceInterface.removeAllTests();
    }
}
