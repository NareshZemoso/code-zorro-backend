package com.zemoso.codezorro.usermanagement.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zemoso.codezorro.usermanagement.exceptions.InvalidUserException;
import com.zemoso.codezorro.usermanagement.model.Recruiter;
import com.zemoso.codezorro.usermanagement.model.RoleType;
import com.zemoso.codezorro.usermanagement.services.interfaces.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/load/{email}")
    public  @ResponseBody Recruiter loadRecruiter(@PathVariable("email") String email) throws InvalidUserException
    {
       return recruiterService.getByEmail(email);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public @ResponseBody Recruiter createRecruiter(@Valid @RequestBody Recruiter recruiter)
    {
        return recruiterService.getOrCreateByEmail(recruiter.getName(),recruiter.getEmail());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/list")
    public @ResponseBody List<Recruiter> getAllRecruiters()
    {
        return recruiterService.getAllRecruiters();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/updaterole")
    public @ResponseBody Recruiter updateRole(@RequestBody Map<String,String> request) throws InvalidUserException
    {
        return recruiterService.updateRole(request.get("email"),RoleType.valueOf(request.get("new_role")));
    }
}
