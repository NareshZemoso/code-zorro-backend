package com.zemoso.codezorro.taskSetService.controller;

import com.zemoso.codezorro.taskSetService.services.serviceInterface.MailingServiceInterface;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.TestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void sendMailToCandidate(@RequestBody Map<String,String> request){
        String link=request.get("tLink");
        String[] mails=request.get("mails").replace("\n","").split(",");
        System.out.println(mails);
    }
}
