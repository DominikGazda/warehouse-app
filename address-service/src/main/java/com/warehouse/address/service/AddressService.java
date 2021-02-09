package com.warehouse.address.service;

import com.warehouse.address.entity.Address;
import com.warehouse.address.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Address saveAddress(Address address) {
        if(address.getAddressId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Address cannot have id");
        }
        return addressRepository.save(address);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Address not found"));
    }
}
