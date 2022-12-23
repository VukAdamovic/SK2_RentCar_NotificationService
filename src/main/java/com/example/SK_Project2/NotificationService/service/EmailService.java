package com.example.SK_Project2.NotificationService.service;

import com.example.SK_Project2.NotificationService.domain.Notification;

public interface EmailService {

    void sendSimpleMessage(Notification notification);
}
