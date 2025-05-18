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

    public void saveClient(Client client) throws MongoException {
        try {
            if (clientRepository.existsById(client.getId())) {
                throw BaseException
                        .exceptionBuilder()
                        .code(409)
                        .message("Client already exists with ID: " + client.getId())
                        .build();
            }
            if (client.getEmail() == null || client.getEmail().isBlank()) {
                throw BaseException
                        .exceptionBuilder()
                        .code(400)
                        .message("Email is required")
                        .build();
            }
            if (client.getName() == null || client.getName().isBlank()) {
                throw BaseException
                        .exceptionBuilder()
                        .code(400)
                        .message("Name is required")
                        .build();
            }
            if (client.getPassword() == null || client.getPassword().isBlank()) {
                throw BaseException
                        .exceptionBuilder()
                        .code(400)
                        .message("Password is required")
                        .build();
            }
            if (client.getId() == null || client.getId().isBlank()) {
                client.setId(null); // MongoDB generará un ObjectId automáticamente
            }
            clientRepository.save(client);
            clientEventsService.publishClientCreated(client);

        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw BaseException
                    .exceptionBuilder()
                    .code(500)
                    .message("Error occurred while saving client: " + e.getMessage())
                    .build();
        }
    }
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

