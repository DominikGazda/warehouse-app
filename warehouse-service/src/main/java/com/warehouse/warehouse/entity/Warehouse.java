package com.warehouse.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="warehouse")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @NotBlank(message = "{com.warehouse.entity.Warehouse.name.NotBlank}")
    private String name;

    @NotNull(message = "{com.warehouse.entity.Warehouse.addressId.NotNull}")
    private Long addressId;
}
