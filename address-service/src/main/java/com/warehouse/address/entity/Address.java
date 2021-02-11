package com.warehouse.address.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank(message = "{com.warehouse.address.country.NotBlank}")
    private String country;

    @NotBlank(message = "{com.warehouse.address.city.NotBlank}")
    private String city;

    @NotBlank(message = "{com.warehouse.address.street.NotBlank}")
    private String street;

    @NotNull(message = "{com.warehouse.address.apartmentNumber}")
    private Integer apartmentNumber;
}
