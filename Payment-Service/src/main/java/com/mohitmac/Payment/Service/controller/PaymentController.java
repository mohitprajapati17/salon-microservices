

package com.mohitmac.Payment.Service.controller;

import com.mohitmac.Payment.Service.domain.PaymentMethod;
import com.mohitmac.Payment.Service.model.PaymentOrder;
import com.mohitmac.Payment.Service.payloadResponse.PaymentLinkResponse;
import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.BookingDTO;
import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.UserDTO;
import com.mohitmac.Payment.Service.service.PaymentService;
import com.mohitmac.Payment.Service.service.client.UserFeingClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserFeingClient userFeingClient;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPayment(@RequestBody BookingDTO bookingDTO, @RequestParam PaymentMethod paymentMethod,@RequestHeader("Authorization") String jwt) throws Exception{
        
        UserDTO userDTO=userFeingClient.getUserByProfile(jwt).getBody();

        PaymentLinkResponse paymentLinkResponse=paymentService.createPayment(userDTO,bookingDTO,paymentMethod);
        return ResponseEntity.ok(paymentLinkResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaymentOrder> getPaymentOrderByid(@PathVariable Long id){
        
        PaymentOrder paymentOrder=paymentService.getPaymentOrderByid(id);
        return ResponseEntity.ok(paymentOrder);
    }




    
}
