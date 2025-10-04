

package com.mohitmac.Payment.Service.controller;

import com.mohitmac.Payment.Service.domain.PaymentMethod;
import com.mohitmac.Payment.Service.model.PaymentOrder;
import com.mohitmac.Payment.Service.payloadResponse.PaymentLinkResponse;
import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.BookingDTO;
import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.UserDTO;
import com.mohitmac.Payment.Service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPayment(@RequestBody BookingDTO bookingDTO, @RequestParam PaymentMethod paymentMethod){
        
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);
        userDTO.setFullName("Mohit");
        userDTO.setEmail("mohit@gmail.com");

        PaymentLinkResponse paymentLinkResponse=paymentService.createPayment(userDTO,bookingDTO,paymentMethod);
        return ResponseEntity.ok(paymentLinkResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaymentOrder> getPaymentOrderByid(@PathVariable Long id){
        
        PaymentOrder paymentOrder=paymentService.getPaymentOrderByid(id);
        return ResponseEntity.ok(paymentOrder);
    }




    
}
