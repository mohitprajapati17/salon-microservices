package com.mohitmac.model;

import com.mohitmac.domain.PaymentMethod;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long amount;
    

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private String paymentLinkId;


    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long salonId;
    @Column(nullable = false)
    private Long bookingId;

}
