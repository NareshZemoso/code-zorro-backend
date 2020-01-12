package com.zemoso.codezorro.taskSetService.services.serviceInterface;

import com.zemoso.codezorro.taskSetService.model.Test;
import com.zemoso.codezorro.taskSetService.repository.TestRepo;

import java.util.List;
import java.util.Optional;

public interface TestServiceInterface {
    Test addTest(Test test);
    void removeTest(Long id);
    void removeAllTests();
    void removeAllQuestionsFromTest(Long testId);
    Optional<Test> findTest(Long id);
    Test updateTest(Test test);
    List<Test> findAll();
    boolean validate(String testLink,String accessKey);
    void setTestRepo(TestRepo testRepo);
}
