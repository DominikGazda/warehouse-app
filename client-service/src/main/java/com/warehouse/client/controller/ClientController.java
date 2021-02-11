package com.warehouse.client.controller;

import com.warehouse.client.entity.Client;
import com.warehouse.client.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/client/")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("")
    public List<Client> getClients(){
        return clientService.getClients();
    }

    @PostMapping("")
    public ResponseEntity<?> saveClient(@Valid @RequestBody Client client, BindingResult errors){
        if(errors.hasErrors())
            clientService.createErrorsMessage(errors);
        Client savedClient = clientService.saveClient(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedClient.getIdClient())
                .toUri();
        return ResponseEntity.created(location).body(savedClient);
    }

    @GetMapping("{id}")
    public Client getClientById(@PathVariable Long id){
        return clientService.getClientById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id,@Valid @RequestBody Client client, BindingResult errors){
        if(errors.hasErrors())
            clientService.createErrorsMessage(errors);
        Client updatedClient = clientService.updateClient(client, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .build().toUri();
        return ResponseEntity.created(location).body(updatedClient);
    }

    @DeleteMapping("{id}")
    public Client deleteClient(@PathVariable Long id){
        return clientService.deleteClient(id);
    }
}
