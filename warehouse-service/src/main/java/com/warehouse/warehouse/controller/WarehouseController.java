package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.common.ResponseTemplate;
import com.warehouse.warehouse.entity.Warehouse;
import com.warehouse.warehouse.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse/")
public class WarehouseController {

    private WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService){
        this.warehouseService = warehouseService;
    }

    @GetMapping("")
    public List<Warehouse> getWarehouses(){
        return warehouseService.getWarehouses();
    }

    @PostMapping("")
    public ResponseEntity<Warehouse> saveWarehouse(@Valid @RequestBody Warehouse warehouse, BindingResult errors){
        if(errors.hasErrors())
            warehouseService.createErrorsMessage(errors);
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

    @PutMapping("{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id,@Valid @RequestBody Warehouse warehouse, BindingResult errors){
        if(errors.hasErrors())
            warehouseService.createErrorsMessage(errors);
        Warehouse updatedWarehouse = warehouseService.updateWarehouse(warehouse, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .build().toUri();
        return ResponseEntity.created(location).body(updatedWarehouse);
    }

    @DeleteMapping("{id}")
    public Warehouse deleteWarehouse(@PathVariable Long id){
        return warehouseService.deleteWarehouse(id);
    }
}
