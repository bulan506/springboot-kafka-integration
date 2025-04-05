package ucr.ac.cr.consumer.service.command;

import ucr.ac.cr.consumer.service.event.AddressChangedEvent;
import ucr.ac.cr.consumer.service.event.CustomerCreatedEvent;
import ucr.ac.cr.consumer.service.event.EventPublisher;
import ucr.ac.cr.consumer.service.model.Address;
import ucr.ac.cr.consumer.service.model.Customer;
import ucr.ac.cr.consumer.service.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CustomerCommandHandler {
    private final CustomerRepository customerRepository;
    private final EventPublisher eventPublisher;

    public CustomerCommandHandler(
            CustomerRepository customerRepository,
            EventPublisher eventPublisher
    ) {
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public String handle(CreateCustomerCommand command) {
        Customer customer = new Customer();
        customer.setName(command.getName());
        customer.setFlooNetworkId(command.getFlooNetworkId());
        customer.setAddress(command.getAddress());

        Customer savedCustomer = customerRepository.save(customer);
        // Publicar el evento de creación
        eventPublisher.publish(new CustomerCreatedEvent(savedCustomer));

        return savedCustomer.getId();
    }
    @Transactional
    public void handle(UpdateAddressCommand command) {
        // Ahora accedemos directamente a los campos del record
        String customerId = command.customerId();  // Directamente con el nombre del campo en el record
        Address newAddress = command.newAddress();  // Igual

        // Buscar al cliente por ID
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Obtener la dirección anterior y actualizarla
        Address oldAddress = customer.getAddress();
        customer.setAddress(newAddress);
        customerRepository.save(customer);

        // Publicar el evento de cambio de dirección
        eventPublisher.publish(new AddressChangedEvent(
                UUID.randomUUID().toString(),
                customer.getId(),
                oldAddress,
                newAddress
        ));
    }
}
