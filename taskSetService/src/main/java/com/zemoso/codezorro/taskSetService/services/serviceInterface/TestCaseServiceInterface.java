package com.zemoso.codezorro.taskSetService.services.serviceInterface;

import com.zemoso.codezorro.taskSetService.model.TestCase;

import java.util.List;
import java.util.Optional;

public interface TestCaseServiceInterface {
    TestCase addTestCase(TestCase testCase);
    void removeTestCase(Long id);
    Optional<TestCase> findTestCase(Long id);
    TestCase updateTestCase(TestCase testCase);
    List<TestCase> findAll();
}
