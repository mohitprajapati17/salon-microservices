package com.mohit.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mohit.payload_DTO.BookingDTO;

@FeignClient("BOOKING-SERVICE")
public interface BookingClient {
    
    
    @GetMapping("/api/booking/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingsById(@PathVariable Long bookingId);
    
    
}
