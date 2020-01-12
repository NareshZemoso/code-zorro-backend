package com.zemoso.codezorro.taskSetService.repository;

import com.zemoso.codezorro.taskSetService.model.TestCase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestCaseRepo extends JpaRepository<TestCase,Long> {
    List<TestCase> findByQuestionId(Long questionId, Pageable pageable);
    Optional<TestCase> findByIdAndQuestionId(Long id, Long questionId);
}
