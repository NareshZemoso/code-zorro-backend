package com.zemoso.codezorro.sessionmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SessionManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionManagementApplication.class, args);
	}

}
