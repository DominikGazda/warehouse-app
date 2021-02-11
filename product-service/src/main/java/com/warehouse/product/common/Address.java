package com.warehouse.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
