package com.analytics.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "car_model_price" )
@Data
@NoArgsConstructor
public class CarModelPrice
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String model;

    private Double price;
}