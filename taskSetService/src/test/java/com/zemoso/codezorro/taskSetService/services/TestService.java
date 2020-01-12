package com.zemoso.codezorro.taskSetService.services;

import com.zemoso.codezorro.taskSetService.helpers.Helper;
import com.zemoso.codezorro.taskSetService.helpers.StorageProvider;
import com.zemoso.codezorro.taskSetService.model.Test;
import com.zemoso.codezorro.taskSetService.repository.TestRepo;
import com.zemoso.codezorro.taskSetService.services.serviceImpl.TestServiceImpl;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestServiceInterface;
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
public class TestService {

    @Mock
    TestRepo testRepo;

    private TestServiceInterface testServiceInterface = new TestServiceImpl();

    @Autowired
    private StorageProvider<Test> storageProvider=new StorageProvider<>();

    @Before
    public void init() {
        Mockito.when(testRepo.save(any())).thenAnswer(r->{
            Test test = r.getArgument(0);
            return storageProvider.addToStore(test);
        });

        Mockito.when(testRepo.findById(anyLong())).thenAnswer(r->{
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
        }).when(testRepo).deleteById(anyLong());

        Mockito.doAnswer(r->{
            storageProvider.clear();
            return null;
        }).when(testRepo).deleteAll();

        Mockito.when(testRepo.findAll()).then(r->storageProvider.getAll());

        testRepo.deleteAll();
        testServiceInterface.setTestRepo(testRepo);
    }

    @org.junit.Test
    public void checkAddTest(){
        int size = testServiceInterface.findAll().size();
        Test test = (Test) Helper.populate(new Test(),Test.class);
        testServiceInterface.addTest(test);
        assertEquals(size+1,testServiceInterface.findAll().size());
    }

    @org.junit.Test
    public void checkIsTestPresent(){
        Test test = (Test) Helper.populate(new Test(),Test.class);
        testServiceInterface.addTest(test);
        assertTrue(testServiceInterface.findTest(test.getId()).isPresent());
    }

    @org.junit.Test
    public void checkIsTestUpdated(){
        Test test = (Test) Helper.populate(new Test(),Test.class);
        testServiceInterface.addTest(test);
        test.setTname("senior");
        test.setCategory("senior");
        testServiceInterface.updateTest(test);
        assertTrue(testServiceInterface.findTest(test.getId()).isPresent());
    }

    @org.junit.Test
    public void checkIsTestDelete(){
        Test test = (Test) Helper.populate(new Test(),Test.class);
        testServiceInterface.addTest(test);
        int size = testServiceInterface.findAll().size();
        testServiceInterface.removeTest(test.getId());
        assertEquals(size-1,testServiceInterface.findAll().size());
    }

    @org.junit.Test
    public void checkIsAllTestsDeleted(){
        for(int i=0;i<10;i++){
            Test test = (Test) Helper.populate(new Test(),Test.class);
            testServiceInterface.addTest(test);
        }
        int size = testServiceInterface.findAll().size();
        testServiceInterface.removeAllTests();
        assertEquals(0,testServiceInterface.findAll().size());
    }

//    @org.junit.Test
//    public void checkTestAccessLinkValidity(){
//        Test test = (Test) Helper.populate(new Test(),Test.class);
//        testServiceInterface.addTest(test);
//
//    }
}