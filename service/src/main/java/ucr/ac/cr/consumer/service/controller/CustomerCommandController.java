package ucr.ac.cr.consumer.service.controller;

import ucr.ac.cr.consumer.service.command.CreateCustomerCommand;
import ucr.ac.cr.consumer.service.command.CustomerCommandHandler;
import ucr.ac.cr.consumer.service.command.UpdateAddressCommand;
import ucr.ac.cr.consumer.service.model.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customers")
public class CustomerCommandController {
    private final CustomerCommandHandler commandHandler;

    public CustomerCommandController(CustomerCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody CreateCustomerCommand command) {
        String customerId = commandHandler.handle(command);
        return ResponseEntity.ok(customerId);
    }
    @PutMapping("/{customerId}/address")
    public ResponseEntity<Void> updateAddress(
            @PathVariable String customerId,
            @RequestBody  Address newAddress) {
        commandHandler.handle(new UpdateAddressCommand(customerId, newAddress));
        return ResponseEntity.accepted().build();
    }
}