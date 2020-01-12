package com.zemoso.codezorro.usermanagement.services.interfaces;

import com.zemoso.codezorro.usermanagement.exceptions.InvalidUserException;
import com.zemoso.codezorro.usermanagement.model.Recruiter;
import com.zemoso.codezorro.usermanagement.model.RoleType;
import com.zemoso.codezorro.usermanagement.repository.RecruiterRepository;

import java.util.List;
import java.util.Optional;

public interface RecruiterService {
    List<Recruiter> getAllRecruiters();
    Recruiter getByEmail(String email) throws InvalidUserException;
    Recruiter createRecruiter(String name, String email);
    Recruiter updateRole(String email,RoleType roleType) throws InvalidUserException;
    void setRecruiterRepository(RecruiterRepository recruiterRepository);
    Optional<Recruiter> getById(Long id);
    void removeById(Long id);
}
