

package com.mohitmac.Payment.Service.controller;

import com.mohitmac.Payment.Service.payloadResponse.payload_DTO.BookingDTO;
import org.springframework.web.bind.annotation.*;
import com.mohitmac.Payment.Service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkReponse> createPayment(@RequestBody BookingDTO bookingDTO, @RequestParam PaymentMethod paymentMethod){
        
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
