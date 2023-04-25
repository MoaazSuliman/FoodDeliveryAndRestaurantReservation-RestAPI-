package com.moaaz.resturant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMessage(String text, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("moaazsuliman1@gmail.com");
        message.setTo(email);
        message.setText(text);
        message.setSubject("Klassy Restaurant");
        javaMailSender.send(message);
    }
}
