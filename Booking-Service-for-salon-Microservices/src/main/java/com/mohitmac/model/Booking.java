package main.java.com.mohitmac.model;

import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.java.com.mohitmac.domain.BookingStatus;

@Data
@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long salonId;

    private Long customerId;


    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ElementCollection
    private Set<Long> serviceIds;

    private BookingStatus status=BookingStatus.PENDING;
    private Integer totalPrice;

}
