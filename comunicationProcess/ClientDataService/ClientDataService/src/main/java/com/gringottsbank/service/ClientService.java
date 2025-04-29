package com.gringottsbank.service;

import com.gringottsbank.model.Address;
import com.gringottsbank.model.Client;
import com.gringottsbank.repository.ClientRepository;
import com.gringottsbank.service.events.ClientEventsService;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientEventsService clientEventsService;

    public void saveClient(Client client) throws MongoException{
        clientRepository.save(client);
        clientEventsService.publishClientCreated(client);
    }

    @Transactional
    public void updateAddresses(String clientId, List<Address> newAddresses) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + clientId));

        client.setAddresses(newAddresses);
        clientRepository.save(client);
        clientEventsService.publishAddressChanged(
                clientId,
                newAddresses
        );
    }
}

