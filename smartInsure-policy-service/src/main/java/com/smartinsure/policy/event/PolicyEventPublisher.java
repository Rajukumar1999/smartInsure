package com.smartinsure.policy.event;

import com.smartinsure.common.event.NotificationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PolicyEventPublisher {

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public PolicyEventPublisher(KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String type, String username, String policyNumber) {

        NotificationEvent event =
                new NotificationEvent(
                        type,
                        username,
                        policyNumber,
                        "Policy event: " + type
                );

        kafkaTemplate.send("policy-events", event);
    }
}
