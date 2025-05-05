package com.gringottsbank.service;

import com.gringottsbank.model.Address;
import com.gringottsbank.model.Client;
import com.gringottsbank.model.exceptions.BaseException;
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
        try {
            clientRepository.save(client);
            clientEventsService.publishClientCreated(client);
        } catch (Exception e) {
            throw BaseException
                    .exceptionBuilder()
                    .code(500)
                    .message("Error occurred while saving client: " + e.getMessage())
                    .build();
        }
    }

    @Transactional
    public void updateAddresses(String clientId, List<Address> newAddresses) {
        try {
            Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new BaseException.BaseExceptionBuilder()
                            .code(404)
                            .message("Client not found with ID: " + clientId)
                            .build());
            client.setAddresses(newAddresses);
            clientRepository.save(client);
            clientEventsService.publishAddressChanged(clientId, newAddresses);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw BaseException
                    .exceptionBuilder()
                    .code(500)
                    .message("Error occurred while updating client addresses: " + e.getMessage())
                    .build();
        }
    }
}

