package com.gringottsbankconsumer.ConsumerDataService.service;

import com.gringottsbankconsumer.ConsumerDataService.events.ClientSavedEvent;
import com.gringottsbankconsumer.ConsumerDataService.model.Client;
import com.gringottsbankconsumer.ConsumerDataService.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ClientDataEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ClientDataEventListener.class);

    @Autowired
    private ClientRepository clientRepository;

    @KafkaListener(topics = "clients3", groupId = "grupo2")
    public void handleClientSavedEvent(ClientSavedEvent event) {
        Client client = event.getData();
        clientRepository.save(client);
        logger.info("Client saved successfully with ID: {}", client.getId());
    }
}
