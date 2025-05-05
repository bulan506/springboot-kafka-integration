package com.gringottsbank.service.events;

import com.gringottsbank.events.AddressChangedEvent;
import com.gringottsbank.events.ClientSavedEvent;
import com.gringottsbank.events.Event;
import com.gringottsbank.events.EventType;
import com.gringottsbank.model.Address;
import com.gringottsbank.model.AddressChangeData;
import com.gringottsbank.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class ClientEventsService {
    private static final Logger log = LoggerFactory.getLogger(ClientEventsService.class);

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.client.name}")
    private String topicClient;

    public void publishClientCreated(Client client) {
        ClientSavedEvent event = new ClientSavedEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setEventType(EventType.CREATED);
        event.setTimestamp(Instant.now().toString());
        event.setEventVersion("1.0");
        event.setData(client); // TODO -- revisar que no sea null
        CompletableFuture<SendResult<String, Event<?>>> future =
                this.producer.send(topicClient, event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("❌ Failed to send event to Kafka. Topic: {}, Event ID: {}",
                        topicClient, event.getEventId(), ex);
            } else {
                log.info("✅ Successfully sent event to Kafka. Topic: {}, Partition: {}, Offset: {}, Event ID: {}",
                        topicClient,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset(),
                        event.getEventId());
            }
        });
    }

    public void publishAddressChanged(String clientId, List<Address> newAddress) {
        AddressChangeData addressChangeData = new AddressChangeData();
        addressChangeData.setClientId(clientId);
        addressChangeData.setNewAddress(newAddress);
        AddressChangedEvent event = new AddressChangedEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setTimestamp(Instant.now().toString());
        event.setEventType(EventType.UPDATED);
        event.setEventVersion("1.0");
        event.setData(addressChangeData);
        CompletableFuture<SendResult<String, Event<?>>> future =
                this.producer.send(topicClient, event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("❌ Failed to send event to Kafka. Topic: {}, Event ID: {}",
                        topicClient, event.getEventId(), ex);
            } else {
                log.info("✅ Successfully sent change address event to Kafka. Topic: {}, Partition: {}, Offset: {}, Event ID: {}",
                        topicClient,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset(),
                        event.getEventId());
            }
        });
    }
}