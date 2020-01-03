package com.zemoso.codezorro.usermanagement.services.implemenations;

import com.zemoso.codezorro.usermanagement.exceptions.InvalidUserException;
import com.zemoso.codezorro.usermanagement.model.Recruiter;
import com.zemoso.codezorro.usermanagement.model.RoleType;
import com.zemoso.codezorro.usermanagement.repository.RecruiterRepository;
import com.zemoso.codezorro.usermanagement.services.interfaces.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    RecruiterRepository recruiterRepository;

    @Transactional
    public Recruiter getOrCreateByEmail(String name,String email)
    {
        return recruiterRepository.findByEmail(email).orElseGet(()->{
            Recruiter recruiter = new Recruiter();
            recruiter.setName(name);
            recruiter.setEmail(email);
            recruiter.setRole(RoleType.GUEST);
            return recruiterRepository.save(recruiter);
        });
    }

    @Override
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    @Transactional
    public Recruiter getByEmail(String email) throws InvalidUserException
    {
        return recruiterRepository.findByEmail(email).orElseThrow(()->new InvalidUserException(email,"No such Recruiter"));
    }

    @Transactional
    public void removeRecruiter(String email)throws InvalidUserException
    {
        recruiterRepository.delete(getByEmail(email));
    }

    @Transactional
    @Override
    public Recruiter updateRole(String email, RoleType roleType) throws InvalidUserException {
        Recruiter recruiter = recruiterRepository.findByEmail(email).orElseThrow(()->new InvalidUserException(email,"No such Recruiter"));
        recruiter.setRole(roleType);
        return recruiterRepository.save(recruiter);
    }


}
