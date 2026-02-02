package com.smartinsure.claim.service.event;

import com.smartinsure.common.event.NotificationEvent;
import org.springframework.kafka.core.KafkaTemplate;

public class ClaimEventPublisher {

    private  final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public ClaimEventPublisher(KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String type, String username, String claimNumber){
        NotificationEvent event =
                new NotificationEvent(
                        type,
                        username,
                        claimNumber,
                        "Claim Event: " +type
                );
        kafkaTemplate.send("claim-events",event);
    }
}
