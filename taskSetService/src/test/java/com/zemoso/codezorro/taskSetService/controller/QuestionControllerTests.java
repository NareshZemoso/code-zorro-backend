package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.helpers.Helper;
import com.zemoso.codezorro.taskSetService.model.Question;
import com.zemoso.codezorro.taskSetService.repository.QuestionRepo;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerTests {
    @Autowired
    private TestRestTemplate restTemplate = null;

    @LocalServerPort
    private int randomServerPort = 0;

    @Autowired
    private QuestionController questionController=null;

    @Autowired
    private QuestionRepo questionRepo=null;

    @Before
    public void cleanUp(){
        questionRepo.deleteAll();
    }

    @org.junit.Test
    public void getDemoApi() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/question/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Question> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Hello",questionController.respond());
    }

    @org.junit.Test
    public void postApiTestToAddQuestion() throws URISyntaxException {
        int questionSize = questionController.getAllQuestions().size();
        System.out.println("TestSize: " + questionSize);
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/question/addQuestion";
        URI uri = new URI(baseUrl);
        Question question = (Question) Helper.populate(new Question(), Question.class);
        questionController.createQuestion(question);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Question> request = new HttpEntity<>(question, headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(questionSize+1,questionController.getAllQuestions().size());
        questionController.deleteQuestionById(question.getId());
        System.out.println("QuestionSize: " + questionSize);
    }

    @org.junit.Test
    public void getApiTestToGetSingleQuestion() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Question question = (Question) Helper.populate(new Question(), Question.class);
        questionController.createQuestion(question);
        String name = question.getQname();
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/question/getQuestion/"+question.getId();
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Question> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(name,questionController.getQuestionById(question.getId()).getQname());
        questionController.deleteQuestionById(question.getId());
    }

    @org.junit.Test
    public void getApiTestToGetAllTests() throws URISyntaxException{
        RestTemplate restTemplate = new RestTemplate();
        int size = questionRepo.findAll().size();
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/question/getAllQuestions";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Question> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(size,questionController.getAllQuestions().size());
    }

    @org.junit.Test
    public void putApiTestToUpdateTest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Question question = (Question) Helper.populate(new Question(), Question.class);
        questionController.createQuestion(question);
        System.out.println(question.getDescription());
        String name = question.getDescription();
        question.setDescription("Vasco-Da-Gama");
        questionController.updateQuestion(question.getId(),question);
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/question/updateQuestion/"+question.getId();
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Question> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);
        assertEquals(200, result.getStatusCodeValue());
        assertNotEquals(name,questionController.getQuestionById(question.getId()).getDescription());
        questionController.deleteQuestionById(question.getId());
        System.out.println(question.getQname());
    }

    @org.junit.Test
    public void deleteApiTestToDeleteQuestionById() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Question question = (Question) Helper.populate(new Question(), Question.class);
        questionController.createQuestion(question);
        int size = questionController.getAllQuestions().size();
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/question/deleteQuestionById/" + question.getId();
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Question> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, requestEntity, String.class);
        //testController.deleteTestById(test.getId());
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(size-1,questionController.getAllQuestions().size());
    }
}
