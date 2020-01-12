package com.zemoso.codezorro.taskSetService.services.serviceImpl;

import com.zemoso.codezorro.taskSetService.model.AccessLink;
import com.zemoso.codezorro.taskSetService.model.Question;
import com.zemoso.codezorro.taskSetService.model.Test;
import com.zemoso.codezorro.taskSetService.repository.AccesslinkRepo;
import com.zemoso.codezorro.taskSetService.repository.TestRepo;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TestServiceImpl implements TestServiceInterface {

    @Override
    public void setTestRepo(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    @Autowired
    private TestRepo testRepo=null;

    @Autowired
    private AccesslinkRepo accesslinkRepo=null;

    @Override
    public Test addTest(Test test) {
        test=testRepo.save(test);
        test.setTestLink("test-"+test.getId());
        return testRepo.save(test);
    }

    @Override
    public void removeAllQuestionsFromTest(Long testId) {
        Set<Question> set=testRepo.findById(testId).get().getQuestions();
        set.clear();
        testRepo.findById(testId).get().setQuestions(set);
    }

    @Override
    public void removeTest(Long id) {
        removeAllQuestionsFromTest(id);
        testRepo.deleteById(id);
    }

    @Override
    public void removeAllTests() {
        for(Test test:testRepo.findAll()){
            removeTest(test.getId());
        }
    }

    @Override
    public Optional<Test> findTest(Long id) {
        return testRepo.findById(id);
    }

    @Override
    public Test updateTest(Test test) {
        return testRepo.save(test);
    }

    @Override
    public List<Test> findAll() {
        return testRepo.findAll();
    }

    @Override
    public boolean validate(String testLink, String accessKey) {
        Optional<AccessLink> accessLink = accesslinkRepo.findByAccesskey(accessKey);
        return accessLink.isPresent() && accessLink.get().getTestlink().equals(testLink);
    }
}
