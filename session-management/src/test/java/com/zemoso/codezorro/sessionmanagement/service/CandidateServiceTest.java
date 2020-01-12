package com.zemoso.codezorro.sessionmanagement.service;
import com.zemoso.codezorro.sessionmanagement.dao.CandidateRepository;
import com.zemoso.codezorro.sessionmanagement.dao.SessionRepository;
import com.zemoso.codezorro.sessionmanagement.entities.Candidate;
import com.zemoso.codezorro.sessionmanagement.entities.Session;
import com.zemoso.codezorro.sessionmanagement.helper.Helper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;




@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateServiceTest {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private CandidateService candidateService;

    @Test
    public void save(){
        Candidate candidate= new Candidate();
        Helper.populate(candidate,Candidate.class);
        candidateService.save(candidate);
        Assert.assertTrue(true);
    }
    @Test
    public void candidate(){
        Candidate candidate= (Candidate) Helper.populate(new Candidate(),Candidate.class);;
       long genId=candidateRepository.findAll().size()+1L;
        candidateService.save(candidate);
        Assert.assertTrue(candidateRepository.findById(genId).isPresent());
    }

    @Test
    public  void startTest(){
        Candidate candidate= candidateRepository.findById(1L).get();
        Session session =candidateService.createSession(candidate);
        Assert.assertTrue(sessionRepository.findById(session.getSessionId()).isPresent());
    }

    @Test
    public void endTest(){
        Candidate candidate= new Candidate();
        Helper.populate(candidate,Candidate.class);
        candidateService.deleteSession(candidate);
        Assert.assertFalse(sessionRepository.existsById(23L));
    }





}
