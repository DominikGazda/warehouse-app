package com.warehouse.order.entity;

import com.warehouse.order.constraint.DateTimeFormatterConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotNull(message = "{com.warehouse.order.entity.Order.clientId.NotNull}")
    private Long clientId;

    @NotNull(message = "{com.warehouse.order.entity.Order.productId.NotNull}")
    private Long productId;

    @DateTimeFormatterConstraint
    private String date;

    private boolean active;
}
