package com.zemoso.codezorro.usermanagement.services;

import com.zemoso.codezorro.usermanagement.exceptions.InvalidUserException;
import com.zemoso.codezorro.usermanagement.helper.Helper;
import com.zemoso.codezorro.usermanagement.helper.StorageProvider;
import com.zemoso.codezorro.usermanagement.model.Recruiter;
import com.zemoso.codezorro.usermanagement.model.RoleType;
import com.zemoso.codezorro.usermanagement.repository.RecruiterRepository;
import com.zemoso.codezorro.usermanagement.services.implemenations.RecruiterServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RecruiterService {

    @Mock
    RecruiterRepository recruiterRepository;


    com.zemoso.codezorro.usermanagement.services.interfaces.RecruiterService recruiterService;


    StorageProvider<Recruiter> storageProvider = new StorageProvider<>();

    @Before
    public void setup()
    {
        Mockito.when(recruiterRepository.save(any())).thenAnswer(r->{
            Recruiter recruiter = r.getArgument(0);
            return storageProvider.addToStore(recruiter);
        });
        Mockito.when(recruiterRepository.findById(anyLong())).thenAnswer(r->{
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
                }
        ).when(recruiterRepository).deleteById(anyLong());
        Mockito.doAnswer(r->{
            storageProvider.clear();
            return null;
        }).when(recruiterRepository).deleteAll();
        Mockito.when(recruiterRepository.findAll()).then(r-> storageProvider.getAll());
        recruiterRepository.deleteAll();
        Mockito.when(recruiterRepository.findByEmail(anyString())).thenAnswer(r->{
            String email = r.getArgument(0);
            List<Recruiter> everybody = recruiterRepository.findAll();
            for(Recruiter somebody:everybody)
                if(somebody.getEmail().equals(email))
                    return Optional.of(somebody);
            return Optional.empty();
        });
        recruiterService = new RecruiterServiceImpl();
        recruiterService.setRecruiterRepository(recruiterRepository);
    }

    @Test
    public void addRecruiter()
    {
        long[] ids = new long[10];
        for(int i=0;i<ids.length;i++) {
            Recruiter recruiter = (Recruiter) Helper.populate(new Recruiter(), Recruiter.class);
            recruiter = recruiterService.createRecruiter(recruiter.getName(),recruiter.getEmail());
            ids[i]=recruiter.getId();
        }
        assertEquals(ids.length,recruiterService.getAllRecruiters().size());
        for (long id : ids) {
            recruiterRepository.deleteById(id);
        }
        assertEquals(0,recruiterService.getAllRecruiters().size());
    }

    @Test(expected = InvalidUserException.class)
    public void checkPresence() throws InvalidUserException
    {

        assertFalse(recruiterService.getById(Long.valueOf(Helper.getRandVal(Long.TYPE).toString())).isPresent());
        long[] ids = new long[10];
        for(int i=0;i<ids.length;i++) {
            Recruiter recruiter = (Recruiter) Helper.populate(new Recruiter(), Recruiter.class);
            recruiter = recruiterService.createRecruiter(recruiter.getName(),recruiter.getEmail());
            assertEquals(recruiterService.getByEmail(recruiter.getEmail()),recruiter);
            ids[i]=recruiter.getId();
        }
        for (long id : ids) assertTrue(recruiterService.getById(id).isPresent());
        for (long id : ids){
            recruiterService.removeById(id);
            assertFalse(recruiterService.getById(id).isPresent());
        }
        recruiterService.getByEmail((String)Helper.getRandVal(String.class));
    }

    @Test
    public void checkRemoval(){
        Recruiter recruiter = (Recruiter) Helper.populate(new Recruiter(),Recruiter.class);
        recruiter = recruiterService.createRecruiter(recruiter.getName(),recruiter.getEmail());
        assertTrue(recruiterService.getById(recruiter.getId()).isPresent());
        assertEquals(1,recruiterService.getAllRecruiters().size());
        recruiterService.removeById(recruiter.getId());
        assertFalse(recruiterService.getById(recruiter.getId()).isPresent());
        assertEquals(0,recruiterService.getAllRecruiters().size());
    }

    @Test(expected = InvalidUserException.class)
    public void checkUpdateRole() throws InvalidUserException
    {

        Recruiter recruiter = (Recruiter) Helper.populate(new Recruiter(),Recruiter.class);
        recruiter = recruiterService.createRecruiter(recruiter.getName(),recruiter.getEmail());
        assertEquals(recruiter.getRole(), RoleType.GUEST);
        recruiterService.updateRole(recruiter.getEmail(),RoleType.ADMIN);
        assertEquals(recruiter.getRole(),RoleType.ADMIN);
        recruiterService.updateRole((String)Helper.getRandVal(String.class),RoleType.ADMIN);
    }
}
