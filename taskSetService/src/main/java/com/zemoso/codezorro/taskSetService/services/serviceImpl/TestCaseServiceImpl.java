package com.zemoso.codezorro.taskSetService.services.serviceImpl;

import com.zemoso.codezorro.taskSetService.model.TestCase;
import com.zemoso.codezorro.taskSetService.repository.TestCaseRepo;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestCaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseServiceInterface {

    @Autowired
    private TestCaseRepo testCaseRepo=null;

    @Override
    public TestCase addTestCase(TestCase testCase) {
        return testCaseRepo.save(testCase);
    }

    @Override
    public void removeTestCase(Long id) {
        testCaseRepo.deleteById(id);
    }

    @Override
    public Optional<TestCase> findTestCase(Long id) {
        return testCaseRepo.findById(id);
    }

    @Override
    public TestCase updateTestCase(TestCase testCase) {
        return testCaseRepo.save(testCase);
    }

    @Override
    public List<TestCase> findAll() {
        return testCaseRepo.findAll();
    }

    @Override
    public void setTestCaseRepo(TestCaseRepo testCaseRepo) {
        this.testCaseRepo=testCaseRepo;
    }
}
