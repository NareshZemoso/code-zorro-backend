package com.zemoso.codezorro.sessionmanagement.service;

import com.zemoso.codezorro.sessionmanagement.dao.AuditRepository;
import com.zemoso.codezorro.sessionmanagement.dao.CandidateRepository;
import com.zemoso.codezorro.sessionmanagement.dao.SessionRepository;
import com.zemoso.codezorro.sessionmanagement.entities.Audit;
import com.zemoso.codezorro.sessionmanagement.entities.Candidate;
import com.zemoso.codezorro.sessionmanagement.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AuditRepository auditRepository;

    public List<Candidate> test(){
        return candidateRepository.findAll();
    }

    public Candidate save(Candidate candidate){
        candidateRepository.save(candidate);
        return candidate;
    }

    public void auditCode(Audit audit){
        auditRepository.save(audit);
    }
    public Session createSession(Candidate candidate){
        Date sessionStartDate= new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sessionStartDate);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date sessionEndDate= calendar.getTime();
        Session session= new Session();
        session.setCandidate(candidate);
        //get this from userManagement service
        session.setRecruiterId("get from usermanagement service");
        //get this from taskSet service
        session.setTestId(1);
        session.setStartSession(sessionStartDate);
        session.setEndSession(sessionEndDate);
        sessionRepository.save(session);
        return session;
    }

    public Session deleteSession(Candidate candidate){
        List<Session> sessions=sessionRepository.findAll();
        Session deletedSession=null;
        for(Session session:sessions){
            if(session.getCandidate().getCandidateId()==candidate.getCandidateId()){
                deletedSession=session;
                sessionRepository.delete(session);
            }
        }
        return deletedSession;
    }

}
