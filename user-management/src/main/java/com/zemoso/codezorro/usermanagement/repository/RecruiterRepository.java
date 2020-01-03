package com.zemoso.codezorro.usermanagement.repository;

import com.zemoso.codezorro.usermanagement.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter,Long> {
    Optional<Recruiter> findByEmail(String email);
}
