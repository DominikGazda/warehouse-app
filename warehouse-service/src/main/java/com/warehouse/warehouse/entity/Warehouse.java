package com.warehouse.warehouse.entity;

import com.warehouse.warehouse.common.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="warehouse")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    private String name;

    private Long addressId;
}
