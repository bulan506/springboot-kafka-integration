package com.gringottsbank.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gringottsbank.model.Address;
import lombok.Data;

import java.time.Instant;

@JsonTypeName("AddressChanged")
public class AddressChangedEvent extends Event<Address> {

    private String customerId;
    private Address oldAddress;
    private Address newAddress;

    public AddressChangedEvent() {
        super();
    }

    public AddressChangedEvent(String eventId, String customerId, Address oldAddress, Address newAddress) {
        super(eventId, "1.0", Instant.now(), newAddress);
        this.customerId = customerId;
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Address getOldAddress() {
        return oldAddress;
    }

    public Address getNewAddress() {
        return newAddress;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setOldAddress(Address oldAddress) {
        this.oldAddress = oldAddress;
    }

    public void setNewAddress(Address newAddress) {
        this.newAddress = newAddress;
    }
}