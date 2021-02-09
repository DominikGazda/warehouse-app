package com.warehouse.order.common.models;

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
