package com.accountmanagement.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    private ClientService clientService;
    @Mock private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(clientRepository);
    }

    @Test
    void getClients() {
        //when
        List<Client> clientList = clientService.getClients();
        //then
        verify(clientRepository).findAll();
        assertThat(clientList).isNotNull();
    }

    @Test
    void getClientById() {
        //given
        Client client = new Client();
        client.setClientId(1);
        //when
        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));
        Client exists = clientService.getClientById(client.getClientId());
        //then
        verify(clientRepository).findById(client.getClientId());
        assertThat(exists).isSameAs(client);
    }
}