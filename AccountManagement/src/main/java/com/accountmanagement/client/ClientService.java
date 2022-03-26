package com.accountmanagement.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Bean
    CommandLineRunner saveClients(){
        return args -> {

            //delete existing clients
            clientRepository.deleteAll();
            log.info("Deleted clients");

            //create new clients
            List<Client> clients = List.of(
                    Client.builder()
                            .clientId(1)
                            .clientName("Anthony")
                            .build(),

                    Client.builder()
                            .clientId(2)
                            .clientName("Dennis")
                            .build()
            );

            clientRepository.saveAll(clients);
            log.info("Created users {}", clients);
        };
    }

    /* READ */
    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(Integer clientId){
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty()) throw new IllegalStateException("Client with id " + clientId + " not found");

        return client.get();
    }

}
