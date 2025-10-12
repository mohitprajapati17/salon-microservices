
package com.mohit.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mohit.mapper.NotificationMapper;
import com.mohit.payload_DTO.BookingDTO;

import com.mohit.model.Notification;
import com.mohit.payload_DTO.NotificationDTO;
import com.mohit.service.NotificationService;
import com.mohit.service.clients.BookingClient;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private BookingClient bookingClient;

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationDTO>> getAllNotificationsByUserId(@PathVariable Long userId){

        List<Notification>  notifications=notificationService.getAllNotificationsByUserId(userId);
        List<NotificationDTO> notificationDTOs=notifications.stream().map(
            notification->{
                BookingDTO bookingDTO=bookingClient.getBookingsById(notification.getBookingId()).getBody();
                return NotificationMapper.toDTO(notification,bookingDTO);
            }
        ).collect(Collectors.toList());
        return ResponseEntity.ok(notificationDTOs);
    }

    @PostMapping()
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody Notification notification){
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    @PutMapping("/{notificationId}")
    public ResponseEntity<NotificationDTO> MarkNotificationAsRead(@PathVariable Long notificationId){
        Notification notification=notificationService.MarkNotificationAsRead(notificationId);
        BookingDTO bookingDTO=bookingClient.getBookingsById(notification.getBookingId()).getBody();
        return ResponseEntity.ok(NotificationMapper.toDTO(notification,bookingDTO));
    }
    
}
