package com.zemoso.codezorro.taskSetService.repository;

import com.zemoso.codezorro.taskSetService.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepo extends JpaRepository<TestCase,Long> {
}
