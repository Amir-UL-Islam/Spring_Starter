package com.example.demo.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// Here We will Implement Java Mail Service
// Here comes the Concept called Logging in Java


@Service
@AllArgsConstructor
public class EmailService implements EmailSender{
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    @Override
    public void send(String to, String email) {

    }
}
