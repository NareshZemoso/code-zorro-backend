package com.zemoso.codezorro.taskSetService.services.serviceImpl;

import com.zemoso.codezorro.taskSetService.model.Question;
import com.zemoso.codezorro.taskSetService.repository.QuestionRepo;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.QuestionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionServiceInterface {
    
    @Autowired
    private QuestionRepo questionRepo=null;
    
    @Override
    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public void removeQuestion(Question question) {
        questionRepo.delete(question);
    }

    @Override
    public void removeQuestion(Long id) {
        questionRepo.deleteById(id);
    }

    @Override
    public Optional<Question> findQuestion(Long id) {
        return questionRepo.findById(id);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public List<Question> findAll() {
        return questionRepo.findAll();
    }
}
