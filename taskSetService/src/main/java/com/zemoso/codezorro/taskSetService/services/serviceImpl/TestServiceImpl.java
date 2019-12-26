package com.zemoso.codezorro.taskSetService.services.serviceImpl;

import com.zemoso.codezorro.taskSetService.model.Test;
import com.zemoso.codezorro.taskSetService.repository.TestRepo;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TestServiceImpl implements TestServiceInterface {

    @Autowired
    private TestRepo testRepo=null;

    @Override
    public Test addTest(Test test) {
        return testRepo.save(test);
    }

    @Override
    public void removeTest(Test test) {
        testRepo.delete(test);
    }

    @Override
    public void removeTest(Long id) {
        testRepo.deleteById(id);
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
}
