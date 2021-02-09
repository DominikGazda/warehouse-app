package com.warehouse.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    private Long warehouseId;

    private String name;

    private Long addressId;
}
