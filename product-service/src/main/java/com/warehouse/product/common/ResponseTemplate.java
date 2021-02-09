package com.warehouse.product.common;

import com.warehouse.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate {

    private Product product;
    private Warehouse warehouse;
    private Address address;
    private Category category;
}
