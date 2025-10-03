package main.java.com.mohitmac.payload_DTO;

import lombok.Data;

@Data
public class ServiceOfferingDTO {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer duration;
    private Long salonId;
    private Long categoryId;
    private String image;
    
    
}
