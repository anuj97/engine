package com.montage.engine.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(String userId, String message) {
        // Stub: Integrate with email, SMS, or push notification provider
        System.out.println("Notify " + userId + ": " + message);
    }
}
