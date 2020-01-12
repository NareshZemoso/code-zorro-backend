package com.zemoso.codezorro.taskSetService.repository;

import com.zemoso.codezorro.taskSetService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question,Long> {
}
