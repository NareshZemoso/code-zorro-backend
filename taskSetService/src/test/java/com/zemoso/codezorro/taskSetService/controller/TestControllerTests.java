package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.services.serviceImpl.TestServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerTests {

    @MockBean
    private TestServiceImpl testServiceImpl;

    private MockMvc mockMvc;

    @InjectMocks
    private TestController testController=null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @org.junit.Test
    public void postApiTestToAddTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/test/addTest/")
                .content("hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().is4xxClientError());
    }
}
