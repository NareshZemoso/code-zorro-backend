package com.zemoso.codezorro.taskSetService.services;

import com.zemoso.codezorro.taskSetService.helpers.Helper;
import com.zemoso.codezorro.taskSetService.helpers.StorageProvider;
import com.zemoso.codezorro.taskSetService.model.TestCase;
import com.zemoso.codezorro.taskSetService.repository.TestCaseRepo;
import com.zemoso.codezorro.taskSetService.services.serviceImpl.TestCaseServiceImpl;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestCaseServiceInterface;
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
public class TestCaseService {
    @Mock
    TestCaseRepo testCaseRepo;

    private TestCaseServiceInterface testCaseServiceInterface = new TestCaseServiceImpl();

    @Autowired
    private StorageProvider<TestCase> storageProvider=new StorageProvider<>();

    @Before
    public void init() {
        Mockito.when(testCaseRepo.save(any())).thenAnswer(r->{
            TestCase testCase = r.getArgument(0);
            return storageProvider.addToStore(testCase);
        });

        Mockito.when(testCaseRepo.findById(anyLong())).thenAnswer(r->{
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
        }).when(testCaseRepo).deleteById(anyLong());

        Mockito.doAnswer(r->{
            storageProvider.clear();
            return null;
        }).when(testCaseRepo).deleteAll();

        Mockito.when(testCaseRepo.findAll()).then(r->storageProvider.getAll());

        testCaseRepo.deleteAll();
        testCaseServiceInterface.setTestCaseRepo(testCaseRepo);
    }

    @org.junit.Test
    public void checkAddTestCase(){
        int size = testCaseServiceInterface.findAll().size();
        TestCase testCase = (TestCase) Helper.populate(new TestCase(),TestCase.class);
        testCaseServiceInterface.addTestCase(testCase);
        assertEquals(size+1,testCaseServiceInterface.findAll().size());
    }

    @org.junit.Test
    public void checkIsTestCasePresent(){
        TestCase testCase = (TestCase) Helper.populate(new TestCase(),TestCase.class);
        testCaseServiceInterface.addTestCase(testCase);
        assertTrue(testCaseServiceInterface.findTestCase(testCase.getId()).isPresent());
    }

    @org.junit.Test
    public void checkIsTestCaseUpdated(){
        TestCase testCase = (TestCase) Helper.populate(new TestCase(),TestCase.class);
        testCaseServiceInterface.addTestCase(testCase);
        testCase.setInput("senior");
        testCase.setOutput("senior");
        testCaseServiceInterface.updateTestCase(testCase);
        assertTrue(testCaseServiceInterface.findTestCase(testCase.getId()).isPresent());
    }

    @org.junit.Test
    public void checkIsTestCaseDelete(){
        TestCase testCase = (TestCase) Helper.populate(new TestCase(),TestCase.class);
        testCaseServiceInterface.addTestCase(testCase);
        int size = testCaseServiceInterface.findAll().size();
        testCaseServiceInterface.removeTestCase(testCase.getId());
        assertEquals(size-1,testCaseServiceInterface.findAll().size());
    }
}
