package com.store.car.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "owner_post" )
@Data
@NoArgsConstructor
public class OwnerPost
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String name;

    private String type;

    private String contactNumber;
}