package com.zemoso.codezorro.taskSetService.services.serviceInterface;

import com.zemoso.codezorro.taskSetService.model.Test;

import java.util.List;
import java.util.Optional;

public interface TestServiceInterface {
    Test addTest(Test test);
    void removeQuestionFromTest(Long testId,Long questionId);
    void removeTest(Long id);
    Optional<Test> findTest(Long id);
    Test updateTest(Test test);
    List<Test> findAll();
    boolean validate(String testLink,String accessKey);
}
