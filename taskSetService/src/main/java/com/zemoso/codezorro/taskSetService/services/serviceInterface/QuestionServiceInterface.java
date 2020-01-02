package com.zemoso.codezorro.taskSetService.services.serviceInterface;

import com.zemoso.codezorro.taskSetService.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QuestionServiceInterface {
    Question addQuestion(Question question);
    void removeTestCaseFromQuestion(Long questionId, Long testcaseId);
    void removeQuestion(Long id);
    Optional<Question> findQuestion(Long id);
    Question updateQuestion(Question question);
    List<Question> findAll();
}
