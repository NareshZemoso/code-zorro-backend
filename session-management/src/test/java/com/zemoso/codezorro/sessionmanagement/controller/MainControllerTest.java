package com.zemoso.codezorro.sessionmanagement.controller;

import com.zemoso.codezorro.sessionmanagement.dao.CandidateRepository;
import com.zemoso.codezorro.sessionmanagement.entities.Candidate;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.zemoso.codezorro.sessionmanagement.utils.RetrieveUtil;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)

@SpringBootTest
public class MainControllerTest {
    @Autowired
    private CandidateRepository candidateRepository;
    @BeforeAll
    public void insert(){
        Candidate candidate= new Candidate();
        candidate.setCandidateAddress("test");
        candidate.setCandidateEmail("sreetejreddy1998@gmail.com");
        candidate.setCandidateId(1);
        candidate.setResume(null);
        candidateRepository.save(candidate);
    }

    @Test
    public void httpStatusCodeOk() throws Exception{
        HttpUriRequest request = new HttpGet( "http://localhost:8085/api/testSession/candidates" );
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void testJsonResponse() throws Exception{
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "http://localhost:8085/api/testSession/candidates" );
        HttpResponse response = HttpClientBuilder.create().build().execute( request );
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        Assert.assertEquals( jsonMimeType, mimeType );
    }

    @Test
    public void jsonPayloadCandidates() throws Exception{
        HttpUriRequest request = new HttpGet( "http://localhost:8085/api/testSession/candidates");
        HttpResponse response = HttpClientBuilder.create().build().execute( request );
        List<Candidate> list= RetrieveUtil.retrieveResourceFromResponse(
                response, List.class);
        System.out.println(list);
        Assert.assertNotNull(list);
    }
}
