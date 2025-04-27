package com.gringottsbankconsumer.ConsumerDataService.events;

import com.gringottsbankconsumer.ConsumerDataService.model.Customer;

import java.time.Instant;
import java.util.UUID;

public class CustomerCreatedEvent extends Event<Customer> {

    private Customer customer;

    public CustomerCreatedEvent(Customer customer) {
        super();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}