package com.mohit.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.mohit.model.Notification;
import com.mohit.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class NotificationEventConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "notification-queue")
    public void NotificationEventListner(Notification notification){
        notificationService.createNotification(notification);
    }


    
}
