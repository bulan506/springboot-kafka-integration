package com.gringottsbank.service;

import com.gringottsbank.model.Address;
import com.gringottsbank.model.Client;
import com.gringottsbank.repository.ClientRepository;
import com.gringottsbank.service.events.ClientEventsService;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void updateAddresses(String clientId, List<Address> newAddresses) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + clientId));
        Address oldAddress = client.getAddresses() != null && !client.getAddresses().isEmpty()
                ? client.getAddresses().get(0)
                : null;
        client.setAddresses(newAddresses);
        clientEventsService.publishAddressChanged(
                clientId,
                newAddresses.get(0),
                oldAddress
        );
    }
}
