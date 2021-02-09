package com.warehouse.order.common;

import com.warehouse.order.common.models.Address;
import com.warehouse.order.common.models.Category;
import com.warehouse.order.common.models.Product;
import com.warehouse.order.common.models.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTemplate {

    private Product product;
    private Warehouse warehouse;
    private Address address;
    private Category category;
}
