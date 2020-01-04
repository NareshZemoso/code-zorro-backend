package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.model.TestCase;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestCaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/testCase")
public class TestCaseController {
    
    @Autowired
    private TestCaseServiceInterface testCaseServiceInterface=null;

    //Create
    @PostMapping("/addTestCase")
    public TestCase createTestCase(@Valid @RequestBody TestCase testCase){
        return testCaseServiceInterface.addTestCase(testCase);
    }

    //Retrieve
    @GetMapping("/getTestCase/{testCaseId}")
    public TestCase getTestCaseById(@PathVariable Long testCaseId) throws Exception {
        return testCaseServiceInterface.findTestCase(testCaseId).orElseThrow(Exception::new);
    }

    //Retrieve
    @GetMapping("/getAllTestCases")
    public List<TestCase> getAllTestCases(){
        return testCaseServiceInterface.findAll();
    }

    //Update
    @PutMapping("/updateTestCase/{testCaseId}")
    public TestCase updateTestCase(@PathVariable Long testCaseId, @Valid @RequestBody TestCase testCase) {
        return testCaseServiceInterface.updateTestCase(testCase);
    }

    //Delete
    @DeleteMapping("/deleteTestCaseById/{testCaseId}")
    public void deleteTestCaseById(@PathVariable Long testCaseId){
        testCaseServiceInterface.removeTestCase(testCaseId);
    }
}
