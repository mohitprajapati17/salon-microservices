package com.mohitmac.Payment.Service.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.mohitmac.Payment.Service.model.PaymentOrder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class BookingEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendBookingUpdateEvent(PaymentOrder paymentOrder){
        rabbitTemplate.convertAndSend("booking-queue", paymentOrder);
    }
}
