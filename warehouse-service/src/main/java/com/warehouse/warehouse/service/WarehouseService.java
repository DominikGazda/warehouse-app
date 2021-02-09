package com.warehouse.warehouse.service;

import com.warehouse.warehouse.common.Address;
import com.warehouse.warehouse.common.ResponseTemplate;
import com.warehouse.warehouse.entity.Warehouse;
import com.warehouse.warehouse.repository.WarehouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WarehouseService {

    private WarehouseRepository warehouseRepository;
    private RestTemplate restTemplate;

    public WarehouseService(WarehouseRepository warehouseRepository, RestTemplate restTemplate){
        this.warehouseRepository = warehouseRepository;
        this.restTemplate = restTemplate;
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        if(warehouse.getWarehouseId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Warehouse cannot have id");
        }
        return warehouseRepository.save(warehouse);
    }

    public ResponseTemplate getWarehouseWithAddress(Long id) {
        ResponseTemplate template = new ResponseTemplate();
        Warehouse warehouse = warehouseRepository.findByWarehouseId(id);

        Address address = restTemplate.getForObject("http://ADDRESS-SERVICE/api/address/"+warehouse.getAddressId(), Address.class);
        template.setWarehouse(warehouse);
        template.setAddress(address);
        return template;
    }
}
