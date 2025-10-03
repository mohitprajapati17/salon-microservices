package com.mohitmac.Payment.Service.payloadResponse.payload_DTO;


import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class SalonDTO {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String city;
    private Long ownerId;
    private LocalTime openTime;
    private LocalTime closeTime;
    private List<String> images;
}

