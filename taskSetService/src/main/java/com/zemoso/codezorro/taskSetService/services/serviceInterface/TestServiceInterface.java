package com.zemoso.codezorro.taskSetService.services.serviceInterface;

import com.zemoso.codezorro.taskSetService.model.Test;

import java.util.List;
import java.util.Optional;

public interface TestServiceInterface {
    Test addTest(Test test);
    void removeTest(Test test);
    void removeTest(Long id);
    Optional<Test> findTest(Long id);
    Test updateTest(Test test);
    List<Test> findAll();
}
