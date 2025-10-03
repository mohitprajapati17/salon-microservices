package com.mohitmac.Payment.Service.repository;

import com.mohitmac.Payment.Service.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository  extends JpaRepository<PaymentOrder ,Long> {

    PaymentOrder findByPaymentLinkId(String id);
}
