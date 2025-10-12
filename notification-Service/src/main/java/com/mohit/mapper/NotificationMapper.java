
package com.mohit.mapper;

import com.mohit.payload_DTO.BookingDTO;

import com.mohit.model.Notification;
import com.mohit.payload_DTO.NotificationDTO;


public class NotificationMapper {

    public static NotificationDTO toDTO(Notification notification, BookingDTO bookingDTO){
        NotificationDTO notificationDTO=new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setType(notification.getType());
        notificationDTO.setIsRead(notification.getIsRead());
        notificationDTO.setUserId(notification.getUserId());
        notificationDTO.setBookingId(bookingDTO.getId());
        notificationDTO.setSalonId(notification.getSalonId());
        notificationDTO.setCreatedAt(notification.getCreatedAt());
        return notificationDTO;
    }
    
}
