package ucr.ac.cr.consumer.service.event;

import ucr.ac.cr.consumer.service.model.Customer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerCreatedEvent {
    private String eventId;
    private String eventType = "CustomerCreated"; // ← Nuevo campo
    private String eventVersion = "1.0";         // ← Versión del esquema
    private Instant timestamp;                  // ← Usar Instant en lugar de LocalDateTime
    private Customer customer;

    public CustomerCreatedEvent(Customer customer) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.customer = customer;
    }

    // Getters necesarios para la serialización
    public String getEventId() {
        return eventId;
    }



    public Customer getCustomer() {
        return customer;
    }

    // Setters (opcionales pero recomendados)
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventVersion() {
        return eventVersion;
    }

    public void setEventVersion(String eventVersion) {
        this.eventVersion = eventVersion;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}