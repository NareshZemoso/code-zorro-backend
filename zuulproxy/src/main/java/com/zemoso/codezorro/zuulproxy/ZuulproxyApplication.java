package com.zemoso.codezorro.zuulproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@CrossOrigin(origins = "http://localhost:3000")
public class ZuulproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulproxyApplication.class, args);
	}

}
