

package main.java.com.mohitmac.payload_DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    private Set<Long> serviceIds;
    
}
