package com.warehouse.warehouse.service;

import com.warehouse.warehouse.common.Address;
import com.warehouse.warehouse.common.ResponseTemplate;
import com.warehouse.warehouse.entity.Warehouse;
import com.warehouse.warehouse.repository.WarehouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    private WarehouseRepository warehouseRepository;
    private RestTemplate restTemplate;

    public WarehouseService(WarehouseRepository warehouseRepository, RestTemplate restTemplate){
        this.warehouseRepository = warehouseRepository;
        this.restTemplate = restTemplate;
    }

    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        if(warehouse.getWarehouseId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Warehouse cannot have id");
        }
        return warehouseRepository.save(warehouse);
    }

    public ResponseTemplate getWarehouseWithAddress(Long id) {
        ResponseTemplate template = new ResponseTemplate();
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find warehouse with provided id"));

        Address address = restTemplate.getForObject("http://ADDRESS-SERVICE/api/address/"+warehouse.getAddressId(), Address.class);
        template.setWarehouse(warehouse);
        template.setAddress(address);
        return template;
    }

    public Warehouse updateWarehouse(Warehouse warehouse, Long id) {
        if(!warehouse.getWarehouseId().equals(id))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Warehouse id must be same as id in path variable");
        Warehouse oldWarehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find warehouse with provided id"));
        return warehouseRepository.save(warehouse);
    }

    public Warehouse deleteWarehouse(Long id){
        Warehouse warehouseToDelete = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find warehouse with provided id"));
        warehouseRepository.delete(warehouseToDelete);
        return warehouseToDelete;
    }

    public void createErrorsMessage(BindingResult errors){
        List<ObjectError> errorList = errors.getAllErrors();
        String message = errorList.stream()
                .map(ObjectError::getDefaultMessage)
                .map(err -> err +" ")
                .map(String::toString)
                .collect(Collectors.joining());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message);
    }
}
