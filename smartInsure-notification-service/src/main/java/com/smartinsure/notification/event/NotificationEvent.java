package com.smartinsure.notification.event;

public class NotificationEvent {

    private String eventType; //policy_approved, claim_approved

    private String username; //RAJU

    private String referenceId;

    private String message; //it happened

    public NotificationEvent() {
    }

    public NotificationEvent(String eventType, String username, String referenceId,String message) {
        this.eventType = eventType;
        this.username = username;
        this.referenceId = referenceId;
        this.message = message;
    }

    public String getEventType() {
        return eventType;
    }

    public String getUsername() {
        return username;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getMessage() {
        return message;
    }
}
