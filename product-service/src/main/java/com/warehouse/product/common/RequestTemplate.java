package com.warehouse.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTemplate {

    private Warehouse warehouse;
    private Address address;
}
