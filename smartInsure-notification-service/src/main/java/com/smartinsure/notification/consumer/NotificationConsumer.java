package com.smartinsure.notification.consumer;

import com.smartinsure.notification.event.NotificationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = {"policy-events","claim-events"},
    groupId = "notification-group")
    public void consume(NotificationEvent event){
        System.out.println("NOTIFICATION RECEIVED");
        System.out.println("Event Type : "+event.getEventType());
        System.out.println("User       : " + event.getUsername());
        System.out.println("Reference  : " + event.getReferenceId());
        System.out.println("Message    : " + event.getMessage());
    }

}
