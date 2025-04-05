package ucr.ac.cr.consumer.service.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topicName;

    public EventPublisher(
            KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${spring.kafka.topics.customer-events}") String topicName
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void publish(Object  event) {
        kafkaTemplate.send(topicName, event);
    }
}