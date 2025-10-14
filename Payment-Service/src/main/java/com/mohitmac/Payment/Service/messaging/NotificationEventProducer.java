package com.mohitmac.Payment.Service.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import com.mohitmac.Payment.Service.model.PaymentOrder;
import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.NotificationDTO;

@AllArgsConstructor
@Component
public class NotificationEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public  void setNotification(Long BookingId , Long  UserId ,Long SalonId){
        NotificationDTO notification=new NotificationDTO();
        notification.setBookingId(BookingId);
        notification.setUserId(UserId);
        notification.setSalonId(SalonId);
        notification.setDescription(" new Booking got confirmed");
        notification.setType("BOOKING");
        rabbitTemplate.convertAndSend("notification-queue", notification);
    }
}
