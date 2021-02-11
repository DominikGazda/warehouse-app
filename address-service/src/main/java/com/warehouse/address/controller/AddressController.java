package com.warehouse.address.controller;

import com.warehouse.address.entity.Address;
import com.warehouse.address.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/address/")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("")
    public List<Address> getAddresses(){
        return addressService.getAddresses();
    }

    @PostMapping("")
    public ResponseEntity<Address> saveAddress(@Valid @RequestBody Address address, BindingResult errors){
        if(errors.hasErrors())
            addressService.createErrorsMessage(errors);
        Address savedAddress = addressService.saveAddress(address);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAddress.getAddressId())
                .toUri();
        return ResponseEntity.created(location).body(savedAddress);
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id,@Valid @RequestBody Address address, BindingResult errors){
        if(errors.hasErrors())
            addressService.createErrorsMessage(errors);
        Address updatedAddress = addressService.updateAddress(address, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .build().toUri();
        return ResponseEntity.created(location).body(updatedAddress);
    }

    @DeleteMapping("{id}")
    public Address deleteAddress(@PathVariable Long id){
        return addressService.deleteAddress(id);
    }
}
