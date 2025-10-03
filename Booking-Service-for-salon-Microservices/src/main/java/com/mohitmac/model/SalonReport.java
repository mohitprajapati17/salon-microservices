package main.java.com.mohitmac.model;

import lombok.Data;

@Data
public class SalonReport {
    
    private Long id;
    private String SalonName;
    private Long salonId;
    private Double totalRevenue;
    private Integer totalBookings;
    private Integer cancelledBookings;
    private Integer totalRefund;
}
