package com.mohitmac.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String price;
    @Column(nullable = false)
    private String duration ;
    @Column(nullable = false)
    private Long salonId;
    @Column(nullable = false)
    private Long categoryId;
    @Column(nullable = false)
    private String image;
}
