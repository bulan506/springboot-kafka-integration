package com.gringottsbank.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gringottsbank.model.Customer;

import java.time.Instant;
import java.util.UUID;

@JsonTypeName("CustomerCreated")
public class CustomerCreatedEvent extends Event<Customer> {

    private Customer customer;

    public CustomerCreatedEvent() {
        super();
    }

    public CustomerCreatedEvent(Customer customer) {
        super(UUID.randomUUID().toString(), "1.0", Instant.now(), customer);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}