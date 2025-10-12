
package com.mohit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.payload_DTO.NotificationDTO;
import com.mohit.service.NotificationService;
import com.mohit.service.clients.BookingClient;

@RestController
@RequestMapping("/api/notification/salon-owner")
public class NotificationSalonOwner {
    
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private BookingClient bookingClient;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<NotificationDTO>> getAllNotificationsBySalonId(@PathVariable Long salonId, @RequestHeader("Authorization") String jwt){
        List<NotificationDTO> notificationDTO=notificationService.getAllNotificationsBySalonId(salonId);
        return ResponseEntity.ok(notificationDTO);
    }
    
}
