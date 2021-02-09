package com.warehouse.warehouse.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    private Long addressId;

    private String country;

    private String city;

    private String street;

    private int apartmentNumber;
}
