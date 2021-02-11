package com.warehouse.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long productId;

    @NotBlank(message = "{com.warehouse.product.entity.Product.name.NotBlank}")
    private String name;

    @NotNull(message = "{com.warehouse.product.entity.Product.quantity.NotNull}")
    private Integer quantity;

    @NotNull(message = "{com.warehouse.product.entity.Product.price.NotNull}")
    private Double price;

    @NotNull(message = "{com.warehouse.product.entity.Product.warehouseId.NotNull}")
    private Long warehouseId;

    @NotNull(message = "{com.warehouse.product.entity.Product.categoryId.NotNull}")
    private Long categoryId;
}
