package com.example.SK_Project2.NotificationService.service.impl;

import com.example.SK_Project2.NotificationService.domain.Email;
import com.example.SK_Project2.NotificationService.domain.Notification;
import com.example.SK_Project2.NotificationService.repository.EmailRepository;
import com.example.SK_Project2.NotificationService.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;
    private EmailRepository emailRepository;

    public EmailServiceImpl(JavaMailSender mailSender, EmailRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }

    @Override
    public void sendSimpleMessage(Notification notification) {

        //Create email and save in DB
        Email email = new Email();
        email.setNotification(notification);
        email.setSubject(notification.getNotificationType().getName());
        email.setContext(notification.getText());
        email.setEmailFrom("TestniMejl stavi");
        email.setEmailTo(notification.getParametar().getEmail());
        email.setDate(String.valueOf(LocalDate.now()));

        emailRepository.save(email);

        //send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getEmailTo());
        message.setTo(email.getSubject());
        message.setText(email.getContext());
        mailSender.send(message);



    }
}
