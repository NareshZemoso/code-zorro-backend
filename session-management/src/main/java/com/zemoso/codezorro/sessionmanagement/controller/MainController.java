package com.zemoso.codezorro.sessionmanagement.controller;
import com.zemoso.codezorro.sessionmanagement.pojos.Code;
import com.zemoso.codezorro.sessionmanagement.pojos.TestResult;
import com.zemoso.codezorro.sessionmanagement.entities.Audit;
import com.zemoso.codezorro.sessionmanagement.entities.Candidate;
import com.zemoso.codezorro.sessionmanagement.entities.Session;
import com.zemoso.codezorro.sessionmanagement.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/testSession")
public class MainController {
    @Autowired
    private CandidateService candidateService;

    @CrossOrigin
    @GetMapping("/candidates")
    public List<Candidate> findAll(){
        return candidateService.test();
    }

    @CrossOrigin
    @PostMapping("/addCandidate")
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate){
        candidateService.save(candidate);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/startTest")
    public ResponseEntity<Session> startTest(@RequestBody Candidate candidate){
       Session session=candidateService.createSession(candidate);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/endTest")
    public ResponseEntity<String> endTest(@RequestBody Candidate candidate){
        if(candidateService.deleteSession(candidate)!=null)
        return new ResponseEntity<>("session deleted",HttpStatus.OK);
        else
        return new ResponseEntity<>("session not found",HttpStatus.BAD_REQUEST);
    }


//this end point mocks compile and run service behavior
    @CrossOrigin
    @PostMapping("/compile")
    public ResponseEntity<TestResult> compile(@RequestBody Code code){
        //send the code to compile service and send the results
        TestResult result= new TestResult();
        result.setStatus(true);
        result.setPassedTest(10);
        result.setTotalTest(10);
        return new ResponseEntity<TestResult>(result,HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/codeAudit")
    public @ResponseBody Audit gb(@RequestBody Audit audit){
    candidateService.auditCode(audit);
        return audit;
    }

}
