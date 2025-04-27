package com.gringottsbank.service;

import com.gringottsbank.events.ClientSavedEvent;
import com.gringottsbank.events.Event;
import com.gringottsbank.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class ClientEventsService {
    private static final Logger log = LoggerFactory.getLogger(ClientEventsService.class);

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.client.name:clients3}")
    private String topicClient;

    public void publish(Client client) {
        ClientSavedEvent event = new ClientSavedEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setTimestamp(Instant.now());
        event.setEventVersion("1.0");
        event.setData(client);

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
}