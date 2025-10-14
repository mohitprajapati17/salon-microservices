package com.mohitmac.Payment.Service.payloadResponse.payload_DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private String type;
    private String description;
    private Boolean isRead=false;

    private Long userId;
    private Long bookingId;
    private Long salonId;
    

    private LocalDateTime createdAt;
}
