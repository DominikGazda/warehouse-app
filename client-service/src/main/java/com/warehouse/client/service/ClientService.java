package com.warehouse.client.service;

import com.warehouse.client.entity.Client;
import com.warehouse.client.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        if(client.getIdClient() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Client cannot have id");
        }
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot find client with provided id"));
    }

    public Client updateClient(Client client, Long id) {
        if(!client.getIdClient().equals(id))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category id must be same as id in path variable");
        return clientRepository.save(client);
    }

    public Client deleteClient(Long id) {
        Client clientToDelete = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot find client with provided id"));
        clientRepository.delete(clientToDelete);
        return clientToDelete;
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
