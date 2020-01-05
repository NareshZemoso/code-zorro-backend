package com.zemoso.codezorro.taskSetService.repository;

import com.zemoso.codezorro.taskSetService.model.AccessLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccesslinkRepo extends JpaRepository<AccessLink,Long> {
    Optional<AccessLink> findByAccesskey(String accesskey);
}
