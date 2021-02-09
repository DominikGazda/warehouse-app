package com.warehouse.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Long addressId;

    private String country;

    private String city;

    private String street;

    private int apartmentNumber;
}
