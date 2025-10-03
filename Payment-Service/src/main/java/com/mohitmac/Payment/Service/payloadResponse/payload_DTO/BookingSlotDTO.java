
package com.mohitmac.Payment.Service.payloadResponse.payload_DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingSlotDTO {
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
