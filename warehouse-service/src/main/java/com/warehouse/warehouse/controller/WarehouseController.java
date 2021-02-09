package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.common.ResponseTemplate;
import com.warehouse.warehouse.entity.Warehouse;
import com.warehouse.warehouse.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/warehouse/")
public class WarehouseController {

    private WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService){
        this.warehouseService = warehouseService;
    }

    @PostMapping("")
    public ResponseEntity<Warehouse> saveWarehouse(@RequestBody Warehouse warehouse){
       Warehouse savedWarehouse = warehouseService.saveWarehouse(warehouse);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedWarehouse.getWarehouseId())
                .toUri();
        return ResponseEntity.created(location).body(savedWarehouse);
    }

    @GetMapping("{id}")
    public ResponseTemplate getWarehouseWithAddress(@PathVariable Long id){
        return warehouseService.getWarehouseWithAddress(id);
    }
}
