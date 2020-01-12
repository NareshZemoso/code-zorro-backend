package com.zemoso.codezorro.taskSetService.services;

import com.zemoso.codezorro.taskSetService.helpers.Helper;
import com.zemoso.codezorro.taskSetService.helpers.StorageProvider;
import com.zemoso.codezorro.taskSetService.model.Question;
import com.zemoso.codezorro.taskSetService.repository.QuestionRepo;
import com.zemoso.codezorro.taskSetService.services.serviceImpl.QuestionServiceImpl;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.QuestionServiceInterface;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class QuestionService {
    @Mock
    QuestionRepo questionRepo;

    private QuestionServiceInterface questionServiceInterface = new QuestionServiceImpl();

    @Autowired
    private StorageProvider<Question> storageProvider=new StorageProvider<>();

    @Before
    public void init() {
        Mockito.when(questionRepo.save(any())).thenAnswer(r->{
            Question question = r.getArgument(0);
            return storageProvider.addToStore(question);
        });

        Mockito.when(questionRepo.findById(anyLong())).thenAnswer(r->{
            Long id = r.getArgument(0);
            if(!storageProvider.checkIfPresent(id))
                return Optional.empty();
            else
                return Optional.of(storageProvider.getFromStore(id));
        });

        Mockito.doAnswer(r->{
            Long  key = r.getArgument(0);
            storageProvider.removeFromStore(key);
            return null;
        }).when(questionRepo).deleteById(anyLong());

        Mockito.doAnswer(r->{
            storageProvider.clear();
            return null;
        }).when(questionRepo).deleteAll();

        Mockito.when(questionRepo.findAll()).then(r->storageProvider.getAll());

        questionRepo.deleteAll();
        questionServiceInterface.setQuestionRepo(questionRepo);
    }

    @org.junit.Test
    public void checkAddQuestion(){
        int size = questionServiceInterface.findAll().size();
        Question question = (Question) Helper.populate(new Question(),Question.class);
        questionServiceInterface.addQuestion(question);
        assertEquals(size+1,questionServiceInterface.findAll().size());
    }

    @org.junit.Test
    public void checkIsQuestionPresent(){
        Question question = (Question) Helper.populate(new Question(),Question.class);
        questionServiceInterface.addQuestion(question);
        assertTrue(questionServiceInterface.findQuestion(question.getId()).isPresent());
    }

    @org.junit.Test
    public void checkIsQuestionUpdated(){
        Question question = (Question) Helper.populate(new Question(),Question.class);
        questionServiceInterface.addQuestion(question);
        question.setDescription("senior");
        question.setExplanation("senior");
        questionServiceInterface.updateQuestion(question);
        assertTrue(questionServiceInterface.findQuestion(question.getId()).isPresent());
    }

    @org.junit.Test
    public void checkIsQuestionDelete(){
        Question question = (Question) Helper.populate(new Question(),Question.class);
        questionServiceInterface.addQuestion(question);
        int size = questionServiceInterface.findAll().size();
        questionServiceInterface.removeQuestion(question.getId());
        assertEquals(size-1,questionServiceInterface.findAll().size());
    }
}
