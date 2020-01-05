package com.zemoso.codezorro.sessionmanagement.dao;

import com.zemoso.codezorro.sessionmanagement.entities.Audit;
import com.zemoso.codezorro.sessionmanagement.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Audit,Long> {
}
