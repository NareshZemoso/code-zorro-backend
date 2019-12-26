package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.model.Test;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestServiceInterface testServiceInterface=null;

    //Create
    @PostMapping("/addTest")
    public Test createTest(@Valid @RequestBody Test test){
        return testServiceInterface.addTest(test);
    }

    //Retrieve
    @GetMapping("/getTest/{testId}")
    public Test getTestById(@PathVariable Long testId) throws Exception {
        return testServiceInterface.findTest(testId).orElseThrow(Exception::new);
    }

    //Retrieve
    @GetMapping("/getAllTests")
    public List<Test> getAllTests(){
        return testServiceInterface.findAll();
    }

    //Update
    @PutMapping("/updateTest/{testId}")
    public Test updateTest(@PathVariable Long testId, @Valid @RequestBody Test test) {
        return testServiceInterface.updateTest(test);
    }

    //Delete
    @DeleteMapping("/deleteTestById/{testId}")
    public void deleteTestById(@PathVariable Long testId){
        testServiceInterface.removeTest(testId);
    }

    //Delete
    @DeleteMapping("/deleteTest/{testId}")
    public void deleteTest(@PathVariable Long testId, @Valid @RequestBody Test test){
        testServiceInterface.removeTest(test);
    }
}
