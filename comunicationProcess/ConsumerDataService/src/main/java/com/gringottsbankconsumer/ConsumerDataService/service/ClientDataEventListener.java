package com.gringottsbankconsumer.ConsumerDataService.service;

import com.gringottsbankconsumer.ConsumerDataService.events.*;
import com.gringottsbankconsumer.ConsumerDataService.model.AddressChangeData;
import com.gringottsbankconsumer.ConsumerDataService.model.Client;
import com.gringottsbankconsumer.ConsumerDataService.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@Service
public class ClientDataEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ClientDataEventListener.class);
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressUpdateService addressUpdateService;
    @KafkaListener(topics = "clients3", groupId = "grupo2")
    public void handleEvent(Event<?> event) {

        switch(event.getEventType()) {
            case CREATED:
                handleClientSavedEvent((ClientSavedEvent) event);
                break;
            case UPDATED:
                handleAddressChangedEvent((AddressChangedEvent) event);
                break;
            default:
                logger.warn("Unknown event type: {}", event.getEventType());
        }
    }

    private void handleClientSavedEvent(ClientSavedEvent event) {
        Client client = event.getData();
        Optional<Client> existingClient = clientRepository.findById(client.getId());
        if(existingClient.isPresent() &&
                event.getEventId().equals(existingClient.get().getLastEventId())) {
            logger.warn("CREATED event has already been processed (ID: {}), ignoring...", event.getEventId());
            return;
        }
        clientRepository.save(client);
        logger.info("Client saved successfully with ID: {}", client.getId());
    }

    private void handleAddressChangedEvent(AddressChangedEvent event) {
        Optional<Client> existingClient = clientRepository.findById(event.getData().getClientId());
        if(existingClient.isPresent() &&
                event.getEventId().equals(existingClient.get().getLastEventId())) {
            logger.warn("UPDATED event has already been processed (ID: {}), ignoring...", event.getEventId());
            return;
        }
        AddressChangeData addressData = event.getData();
        addressUpdateService.updateClientAddress(addressData);
        logger.info("Address changed for client ID: {}", addressData.getClientId());
    }

}
