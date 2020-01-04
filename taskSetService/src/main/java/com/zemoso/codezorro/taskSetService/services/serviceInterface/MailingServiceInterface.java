package com.zemoso.codezorro.taskSetService.services.serviceInterface;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface MailingServiceInterface {
    void sendMail() throws IOException;
}
