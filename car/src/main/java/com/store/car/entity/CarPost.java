package com.store.car.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "car_post" )
@Data
@NoArgsConstructor
public class CarPost
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String model;

    private String brand;

    private Double price;

    private String description;

    private String engineVersion;

    private String city;

    private String createdDate;

    @Column( name = "car_post_contact" )
    private String contact;

    @ManyToOne
    @JoinColumn( name="owner_post_id", nullable = false, referencedColumnName = "id" )
    private OwnerPost ownerPost;
}