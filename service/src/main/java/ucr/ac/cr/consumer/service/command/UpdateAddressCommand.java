package ucr.ac.cr.consumer.service.command;


import ucr.ac.cr.consumer.service.model.Address;


public record UpdateAddressCommand(String customerId, Address newAddress) {
}