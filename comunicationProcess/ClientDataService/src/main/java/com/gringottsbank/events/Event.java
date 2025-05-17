package com.gringottsbank.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "eventType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClientSavedEvent.class, name = "CREATED"),
        @JsonSubTypes.Type(value = AddressChangedEvent.class, name = "UPDATED")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Event<T> {

    private String eventId;
    private String eventVersion;
    private String timestamp;
    private EventType eventType;
    private T data;
}