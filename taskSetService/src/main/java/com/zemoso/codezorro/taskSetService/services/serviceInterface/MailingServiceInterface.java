package com.zemoso.codezorro.taskSetService.services.serviceInterface;

import com.sendgrid.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface MailingServiceInterface {
    void sendMail(String[] mails,String testLink) throws IOException;
}
