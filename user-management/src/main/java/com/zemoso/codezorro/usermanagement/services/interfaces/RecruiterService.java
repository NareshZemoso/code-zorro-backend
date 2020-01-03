package com.zemoso.codezorro.usermanagement.services.interfaces;

import com.zemoso.codezorro.usermanagement.exceptions.InvalidUserException;
import com.zemoso.codezorro.usermanagement.model.Recruiter;
import com.zemoso.codezorro.usermanagement.model.RoleType;

import java.util.List;

public interface RecruiterService {
    List<Recruiter> getAllRecruiters();
    Recruiter getByEmail(String email) throws InvalidUserException;
    Recruiter getOrCreateByEmail(String name,String email);
    void removeRecruiter(String email)throws InvalidUserException;
    Recruiter updateRole(String email,RoleType roleType) throws InvalidUserException;
}
