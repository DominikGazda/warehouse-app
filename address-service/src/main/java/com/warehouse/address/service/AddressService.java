package com.warehouse.address.service;

import com.warehouse.address.entity.Address;
import com.warehouse.address.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address cannot have id");
        }
        return addressRepository.save(address);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address not found"));
    }

    public Address updateAddress(Address address, Long id) {
        if(!address.getAddressId().equals(id))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Address id must be same as id in path variable");
        Address oldAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address not found"));
        return addressRepository.save(address);
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

    public Address deleteAddress(Long id) {
        Address addressToDelete = addressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address not found"));
        addressRepository.delete(addressToDelete);
        return addressToDelete;
    }
}
