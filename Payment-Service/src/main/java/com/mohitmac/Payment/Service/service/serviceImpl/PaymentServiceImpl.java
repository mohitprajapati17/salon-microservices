package com.mohitmac.Payment.Service.service.serviceImpl;

import com.mohitmac.Payment.Service.domain.PaymentMethod;
import com.mohitmac.Payment.Service.domain.PaymentOrderStatus;
import com.mohitmac.Payment.Service.model.PaymentOrder;
import com.mohitmac.Payment.Service.payloadResponse.PaymentLinkResponse;
import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.BookingDTO;
import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.UserDTO;
import com.mohitmac.Payment.Service.repository.Repository;
import com.mohitmac.Payment.Service.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private Repository repository;
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;



    @Override
    public PaymentLinkResponse createPayment(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod) {
        Long  amount = (long)bookingDTO.getTotalPrice();
        PaymentOrder  order=new PaymentOrder();
        order.setAmount(amount);
        order.setPaymentMethod(paymentMethod);
        order.setSalonId(bookingDTO.getSalonId());
        order.setBookingId(bookingDTO.getId());
        order.setUserId(userDTO.getId());

        PaymentOrder savedOrder=repository.save(order);
        PaymentLinkResponse paymentLinkResponse=new PaymentLinkResponse();
        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){

            PaymentLink paymentLink =createRazorpayPaymentLink(userDTO,savedOrder.getAmount(),savedOrder.getId());

            String paymentUrl=paymentLink.get("short_url");
            String paymentUrlId=paymentLink.get("id");

            paymentLinkResponse.setPayment_link_url(paymentUrl);

            paymentLinkResponse.setPayment_link_id(paymentUrlId);

            savedOrder.setPaymentLinkId(paymentUrlId);
            repository.save(savedOrder);

        }
        else{
            String paymentUrl=createStripePaymentLink(userDTO,savedOrder.getAmount(),savedOrder.getId());
            paymentLinkResponse.setPayment_link_url(paymentUrl);

        }
        return paymentLinkResponse;



    }

    @Override
    public PaymentOrder getPaymentOrderByid(Long id) {
         PaymentOrder paymentOrder=repository.findById(id).orElse(null);
         if(paymentOrder==null){
             throw new RuntimeException("Payment Order not found");
         }
         return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return repository.findByPaymentLinkId(paymentId);
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO userDTO, Long Amount, Long orderId) {
        try {
            Long amount = Amount * 100;
            RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpaySecret);
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");

            JSONObject customer = new JSONObject();
            customer.put("name", userDTO.getFullName());
            customer.put("email", userDTO.getEmail());
            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("email", true);

            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("remainder_enabled", true);
            paymentLinkRequest.put("callback_url", "http://localhost:3000/payment-success/" + orderId);
            paymentLinkRequest.put("callback_method", "get");

            return razorpayClient.paymentLink.create(paymentLinkRequest);
        } catch (Exception e) {
            throw new RuntimeException("Error creating Razorpay payment link", e);
        }
    }

    @Override
    public String createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) {
        try {
            Stripe.apiKey = stripeApiKey;
            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:3000/payment-success/" + orderId)
                    .setCancelUrl("http://localhost:3000/payment-failure/" + orderId)
                    .addLineItem(SessionCreateParams.LineItem.builder()
                            .setQuantity(1L)
                            .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("usd")
                                    .setUnitAmount(amount * 100)
                                    .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                            .setName("salon appointment  booking")
                                            .build())
                                    .build())
                            .build())
                    .build();
            Session session = Session.create(params);

            return session.getUrl();
        } catch (Exception e) {
            throw new RuntimeException("Error creating Stripe payment link", e);
        }
    }


    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, String PaymentId, String PaymentLinkId) {
        if (paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
            if (paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)) {
                try {
                    RazorpayClient razorpay = new RazorpayClient(razorpayApiKey, razorpaySecret);
                    Payment payment = razorpay.payments.fetch(PaymentId);
                    Integer amount = payment.get("amount");
                    String status = payment.get("status");
                    if (status.equals("captured")) {
                        paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                        repository.save(paymentOrder);
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    throw new RuntimeException("Error processing Razorpay payment", e);
                }

            } else {
                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                repository.save(paymentOrder);
                return true;
            }
        }
        return false;
    }
}
