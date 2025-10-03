package com.mohitmac.Payment.Service.model;

import com.mohitmac.Payment.Service.domain.PaymentMethod;
import com.mohitmac.Payment.Service.domain.PaymentOrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private PaymentOrderStatus status=PaymentOrderStatus.PENDING;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private String paymentLink;


    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long salonId;
    @Column(nullable = false)
    private Long bookingId;

}
