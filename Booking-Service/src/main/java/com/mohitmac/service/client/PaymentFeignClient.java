package com.mohitmac.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.mohitmac.domain.PaymentMethod;
import com.mohitmac.payload_DTO.BookingDTO;
import com.mohitmac.payload_DTO.PaymentLinkResponse;

@FeignClient("PAYMENT-SERVICE")
public interface PaymentFeignClient {
    @PostMapping("/api/payment/create")
    public ResponseEntity<PaymentLinkResponse> createPayment(@RequestBody BookingDTO bookingDTO, @RequestParam PaymentMethod paymentMethod,@RequestHeader("Authorization") String jwt);
        
       

    
} 

