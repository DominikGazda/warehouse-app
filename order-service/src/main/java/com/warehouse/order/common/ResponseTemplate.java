package com.warehouse.order.common;

import com.warehouse.order.common.models.Client;
import com.warehouse.order.common.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate {

    private Long orderId;
    private Client client;
    private Product product;
    private String date;
    private boolean active;
}
