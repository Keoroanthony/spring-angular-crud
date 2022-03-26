package com.accountmanagement.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<?> getClients(){
        List<Client> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("{clientId}")
    public ResponseEntity<?> getClientById(@PathVariable Integer clientId){
        try {
            Client client = clientService.getClientById(clientId);
            return ResponseEntity.ok(client);
        } catch (IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
