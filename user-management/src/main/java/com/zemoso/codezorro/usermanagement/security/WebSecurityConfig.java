package com.zemoso.codezorro.usermanagement.security;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private String auth0Issuer = "https://tarun47.auth0.com/";
    private String apiAudience = "http://localhost:8080/api";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer.forRS256(apiAudience,auth0Issuer)
                .configure(http)
                .cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/api/recruiter/list").hasAuthority("ADMIN")
                .antMatchers("/api/recruiter/updateRole").hasAuthority("ADMIN")
                .anyRequest().permitAll().and().addFilter(new OrganisationFilter(authenticationManager()));
    }
}
