package com.example.demo.email;

import lombok.AllArgsConstructor;
//import org.aspectj.bridge.MessageHandler;
//import org.hibernate.pretty.MessageHelper;
//import org.hibernate.pretty.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

// Here We will Implement Java Mail Service
// Here comes the Concept called Logging in Java


@Service
@AllArgsConstructor
public class EmailService implements EmailSender{
    // This LoggerFactory will RETURN any log/logs in the Console
    // So, What's Difference between System.out.print(errorMessage);
    // The slf4j interface dose make sense HERE
    // slf4j is using Logback under the Hud.
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    @Override
    @Async
    public void send(String to, String email) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            // Creating The Email Body
            messageHelper.setText(email, true);
            messageHelper.setTo(to);
            messageHelper.setSubject("Confirm Your Token");
            messageHelper.setFrom("Amir@message.com");

            // Sending the Mail
            mailSender.send(mimeMessage);

        }catch (MessagingException e){
            // The Logger Exception defined here is for Developer
            LOGGER.error("Failed To Send Email", e);
            throw new IllegalStateException("Failed To Send Email");
        }
    }
}
