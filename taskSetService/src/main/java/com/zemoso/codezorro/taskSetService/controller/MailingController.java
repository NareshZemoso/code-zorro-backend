package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.model.AccessLink;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.MailingServiceInterface;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/mail")
public class MailingController {

    @Autowired
    private TestServiceInterface testServiceInterface=null;

    @Autowired
    private MailingServiceInterface mailingServiceInterface=null;

    @PostMapping("/dispatch")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> sendMailToCandidate(@RequestBody Map<String,String> request){
        String link=request.get("tLink");
        String[] mails=request.get("mails").replace("\n","").split(",");
       try{
           mailingServiceInterface.sendMail(mails,link);
       }
       catch (Exception e)
       {
           return ResponseEntity.badRequest().body("Invalid request");
       }
        return ResponseEntity.ok("Hello");
    }
}
