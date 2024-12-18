package com.example.performance_management.utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class HelperUtil {

    public static final int MINUTES_PER_HOUR = 60;
    private final JavaMailSender javaMailSender;

    public HelperUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Async
    public Future<String> sendMail(String companyEmail, String password) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom("kaustubhdeokarsde@gmail.com");
        simpleMessage.setTo(companyEmail);
        simpleMessage.setSubject("Use this password to login into the platform.");
        simpleMessage.setText("Password:" + password);
        javaMailSender.send(simpleMessage);
        return CompletableFuture.completedFuture(password);
    }
}
