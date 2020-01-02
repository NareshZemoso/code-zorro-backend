package com.zemoso.codezorro.taskSetService.services.serviceImpl;

import com.zemoso.codezorro.taskSetService.model.Question;
import com.zemoso.codezorro.taskSetService.model.TestCase;
import com.zemoso.codezorro.taskSetService.repository.QuestionRepo;
import com.zemoso.codezorro.taskSetService.repository.TestCaseRepo;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.QuestionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionServiceInterface {
    
    @Autowired
    private QuestionRepo questionRepo=null;

    @Autowired
    private TestCaseRepo testCaseRepo=null;
    
    @Override
    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public void removeTestCaseFromQuestion(Long questionId,Long testcaseId) {
        Set<TestCase> set=questionRepo.findById(questionId).get().getTestCases();
        set.remove(testCaseRepo.findById(testcaseId).get());
        questionRepo.findById(questionId).get().setTestCases(set);
    }

    @Override
    public void removeQuestion(Long id) {
        questionRepo.deleteById(id);
    }

    @Override
    public Optional<Question> findQuestion(Long id) {
        return questionRepo.findById(id);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public List<Question> findAll() {
        return questionRepo.findAll();
    }
}
