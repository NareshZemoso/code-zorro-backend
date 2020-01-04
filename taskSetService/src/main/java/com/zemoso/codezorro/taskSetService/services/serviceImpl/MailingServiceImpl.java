package com.zemoso.codezorro.taskSetService.services.serviceImpl;

import com.sendgrid.*;
import com.zemoso.codezorro.taskSetService.repository.AccesslinkRepo;
import com.zemoso.codezorro.taskSetService.services.serviceInterface.MailingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
public class MailingServiceImpl implements MailingServiceInterface {

    @Autowired
    private AccesslinkRepo accesslinkRepo=null;

    @Override
    public void sendMail() throws IOException {
        Email from = new Email("nikhilpavansai.machavaram@zemosolabs.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("tarun.gudipati@zemosolabs.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
    }
}
