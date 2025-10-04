
package com.mohitmac.payload_DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingSlotDTO {
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
