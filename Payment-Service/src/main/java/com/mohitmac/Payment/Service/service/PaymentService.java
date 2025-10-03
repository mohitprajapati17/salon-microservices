package com.mohitmac.Payment.Service.service;

import com.mohitmac.Payment.Service.domain.PaymentMethod;
import com.mohitmac.Payment.Service.model.PaymentOrder;
import com.mohitmac.Payment.Service.payloadResponse.PaymentLinkResponse;
import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.SalonDTO;
import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.UserDTO;
import com.razorpay.PaymentLink;

public interface PaymentService {

    PaymentLinkResponse createPayment(UserDTO  userDTO, SalonDTO salonDTO, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderByid(Long id);

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO userDTO ,  Long  amount , Long orderId);

    String createStripePaymentLink(UserDTO userDTO ,  Long  amount , Long orderId);
}
