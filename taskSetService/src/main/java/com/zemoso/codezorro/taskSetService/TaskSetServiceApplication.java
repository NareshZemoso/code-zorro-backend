package com.zemoso.codezorro.taskSetService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableJpaAuditing
@CrossOrigin("localhost:3000")
public class TaskSetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSetServiceApplication.class, args);
	}

}