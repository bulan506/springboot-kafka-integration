package ucr.ac.cr.consumer.service.event;

import ucr.ac.cr.consumer.service.model.Address;

import java.time.LocalDateTime;

public class AddressChangedEvent {
    private String eventId;
    private String customerId;
    private Address oldAddress;
    private Address newAddress;
    private LocalDateTime timestamp = LocalDateTime.now();
    // Constructor
    public AddressChangedEvent(
            String eventId,
            String customerId,
            Address oldAddress,
            Address newAddress
    ) {
        this.eventId = eventId;
        this.customerId = customerId;
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }
    // Constructor sin parámetros
    public AddressChangedEvent() {
    }
    // Getters y Setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public Address getOldAddress() { return oldAddress; }
    public void setOldAddress(Address oldAddress) { this.oldAddress = oldAddress; }
    public Address getNewAddress() { return newAddress; }
    public void setNewAddress(Address newAddress) { this.newAddress = newAddress; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

}
