package com.gringottsbankconsumer.ConsumerDataService.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
@NoArgsConstructor
public abstract class Event<T> {
    private String id;
    private Date date;
    private EventType type;
    private T data;
}
