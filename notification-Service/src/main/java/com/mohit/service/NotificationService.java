package com.mohit.service;

import com.mohit.model.Notification;
import com.mohit.payload_DTO.NotificationDTO;
import java.util.List;
public interface NotificationService {
    
    public NotificationDTO createNotification(Notification notification);
    public List<Notification> getAllNotificationsByUserId(Long userId);
    public List<Notification> getNotificationBySalonId(Long salonId);
    public List<NotificationDTO> getAllNotificationsBySalonId(Long salonId);
    public Notification MarkNotificationAsRead(Long notificationId);
}
