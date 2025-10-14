package com.mohitmac.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.mohitmac.model.PaymentOrder;
import com.mohitmac.service.BookingService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BookingEventConsumer {

    private final BookingService bookingService;
    
    @RabbitListener(queues = "booking-queue")
    public void BookingEventListner(PaymentOrder paymentOrder){
        bookingService.bookingSuccess(paymentOrder);
    }
    
}
