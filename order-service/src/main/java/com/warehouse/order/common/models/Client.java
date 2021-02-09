package com.warehouse.order.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Long idClient;

    private String name;

    private String lastName;

    private String address;

    private String phoneNumber;
}
