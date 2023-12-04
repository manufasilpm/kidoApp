package com.kidoApp.kidoApp.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotificationEmail(String to, String subject, String content) throws MessagingException {


        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom("abc@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);



        javaMailSender.send(message);

        System.out.println("mail send successfully"+ to+" "+ subject+" "+content);
    }
}