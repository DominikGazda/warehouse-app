package com.warehouse.warehouse.common;

import com.warehouse.warehouse.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate {

    private Warehouse warehouse;
    private Address address;
}
