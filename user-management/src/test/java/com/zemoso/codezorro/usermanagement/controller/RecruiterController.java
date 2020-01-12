package com.zemoso.codezorro.usermanagement.controller;

import com.zemoso.codezorro.usermanagement.services.RecruiterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(RecruiterController.class)
public class RecruiterController {
    @Mock
    RecruiterService recruiterService;
}
