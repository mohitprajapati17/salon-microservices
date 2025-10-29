package com.mohit.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mohit.mapper.NotificationMapper;
import com.mohit.payload_DTO.BookingDTO;

import com.mohit.model.Notification;
import com.mohit.payload_DTO.NotificationDTO;
import com.mohit.repository.NotificationRepository;
import com.mohit.service.NotificationService;
import com.mohit.service.clients.BookingClient;

@Service
public class NotificationServiceImpl implements NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private BookingClient bookingClient;

    @Override
    public NotificationDTO createNotification(Notification notification) {
        ResponseEntity<BookingDTO> response = bookingClient.getBookingsById(notification.getBookingId());
        BookingDTO bookingDTO = response.getBody();
        NotificationDTO notificationDTO=NotificationMapper.toDTO(notification,bookingDTO);
        return notificationDTO;
    }

    @Override
    public List<Notification> getAllNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getNotificationBySalonId(Long salonId) {
        return notificationRepository.findBySalonId(salonId);
    }

    @Override
    public List<NotificationDTO> getAllNotificationsBySalonId(Long salonId) {
        List<Notification> notifications = notificationRepository.findBySalonId(salonId);
        return notifications.stream().map(notification -> {
            ResponseEntity<BookingDTO> response = bookingClient.getBookingsById(notification.getBookingId());
            BookingDTO bookingDTO = response.getBody();
            return NotificationMapper.toDTO(notification, bookingDTO);
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Notification MarkNotificationAsRead(Long notificationId) {
        Notification notification=notificationRepository.findById(notificationId).orElseThrow(()->new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        return notificationRepository.save(notification);
    }
}
