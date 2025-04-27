package com.gringottsbank.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.Instant;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "eventType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClientSavedEvent.class, name = "ClientSaved")
})
@Data
public abstract class Event<T> {

    private String eventId;
    private String eventVersion;
    private Instant timestamp;
    private T data;

    public Event() {
    }

    public Event(String eventId, String eventVersion, Instant timestamp, T data) {
        this.eventId = eventId;
        this.eventVersion = eventVersion;
        this.timestamp = timestamp;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventVersion() {
        return eventVersion;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventVersion(String eventVersion) {
        this.eventVersion = eventVersion;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}