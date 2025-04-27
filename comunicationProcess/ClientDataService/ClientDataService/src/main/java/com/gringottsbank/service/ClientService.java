package com.gringottsbank.service;

import com.gringottsbank.model.Client;
import com.gringottsbank.repository.ClientRepository;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientEventsService clientEventsService;

    public void saveClient(Client client) throws MongoException{
        clientRepository.save(client);
        clientEventsService.publish(client);
    }
}
