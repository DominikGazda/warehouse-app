package com.warehouse.order.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long productId;

    private String name;

    private int quantity;

    private double price;

    private Long warehouseId;

    private Long categoryId;
}
