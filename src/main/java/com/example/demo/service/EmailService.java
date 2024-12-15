package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("saiklogin0123@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        mailSender.send(message);
    }

    public String sendEmail(String to,String subject, String text) {
    	subject = "Welcome to The metaProject";
    	text = "Hi , This is sai krishna from metaProject member, so if you any concerns let me know and give me reply this mail.";
        sendSimpleMessage(to, subject, text);
        return "Email sent successfully";
    }
}