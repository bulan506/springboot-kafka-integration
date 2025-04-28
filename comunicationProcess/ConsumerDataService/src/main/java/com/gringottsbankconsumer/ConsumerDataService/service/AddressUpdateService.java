package com.gringottsbankconsumer.ConsumerDataService.service;

import com.gringottsbankconsumer.ConsumerDataService.model.Address;
import com.gringottsbankconsumer.ConsumerDataService.model.AddressChangeData;
import com.gringottsbankconsumer.ConsumerDataService.model.Client;
import com.gringottsbankconsumer.ConsumerDataService.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AddressUpdateService {

    private static final Logger logger = LoggerFactory.getLogger(AddressUpdateService.class);

    @Autowired
    private ClientRepository clientRepository;

    public void updateClientAddress(AddressChangeData addressData) {
        Client client = clientRepository.findById(addressData.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + addressData.getClientId()));

        validateAddressData(addressData);
        updateAddresses(client, addressData.getNewAddress(), addressData.getOldAddress());
        clientRepository.save(client);
        logger.info("Address updated for client ID: {}", addressData.getClientId());
    }

    private void validateAddressData(AddressChangeData addressData) {
        if (addressData.getNewAddress() == null) {
            throw new IllegalArgumentException("New address cannot be null");
        }
    }

    private void updateAddresses(Client client, Address newAddress, Address oldAddress) {
        if (client.getAddresses() == null) {
            client.setAddresses(new ArrayList<>());
        }

        if (oldAddress != null) {
            boolean replaced = false;
            for (int i = 0; i < client.getAddresses().size(); i++) {
                if (client.getAddresses().get(i).equals(oldAddress)) {
                    client.getAddresses().set(i, newAddress);
                    replaced = true;
                    break;
                }
            }
            if (!replaced) {
                client.getAddresses().add(newAddress);
            }
        } else {
            client.getAddresses().add(newAddress);
        }
    }
}