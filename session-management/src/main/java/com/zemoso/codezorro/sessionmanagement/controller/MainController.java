package com.zemoso.codezorro.sessionmanagement.controller;
import com.zemoso.codezorro.sessionmanagement.entities.Candidate;
import com.zemoso.codezorro.sessionmanagement.entities.Session;
import com.zemoso.codezorro.sessionmanagement.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping("/candidates")
    public List<Candidate> findAll(){
        return candidateService.test();
    }

    @PostMapping("/addCandidate")
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate){
        candidateService.save(candidate);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @PostMapping("/startTest")
    public ResponseEntity<Session> startTest(@RequestBody Candidate candidate){
       Session session=candidateService.createSession(candidate);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }

    @DeleteMapping("/endTest")
    public ResponseEntity<String> endTest(@RequestBody Candidate candidate){
        if(candidateService.deleteSession(candidate)!=null)
        return new ResponseEntity<>("session deleted",HttpStatus.OK);
        else
        return new ResponseEntity<>("session not found",HttpStatus.BAD_REQUEST);
    }

}
