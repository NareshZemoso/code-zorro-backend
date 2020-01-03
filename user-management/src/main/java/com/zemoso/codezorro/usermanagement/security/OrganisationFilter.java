package com.zemoso.codezorro.usermanagement.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrganisationFilter extends BasicAuthenticationFilter {


    public OrganisationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null)
            throw new ServletException("Invalid Request, No authorization");
        String token = authHeader.substring(authHeader.lastIndexOf(" ")+1);
        DecodedJWT decodedJWT = JWT.decode(token);
        String userEmail = decodedJWT.getClaim("https://www.codezorro.com/email").asString();
        //Lifting Mail Constraint For Testing
        if(!userEmail.contains("@zemosolabs.com")) {
             throw new ServletException("Invalid Resource Access Attempt not from Organisation");
        }
        chain.doFilter(request,response);
    }
}
