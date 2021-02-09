package com.warehouse.order.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Long addressId;

    private String country;

    private String city;

    private String street;

    private int apartmentNumber;
}
