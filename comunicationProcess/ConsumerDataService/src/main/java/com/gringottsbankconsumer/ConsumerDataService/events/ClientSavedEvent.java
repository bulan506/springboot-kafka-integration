package com.gringottsbankconsumer.ConsumerDataService.events;

import com.gringottsbankconsumer.ConsumerDataService.model.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientSavedEvent extends Event<Client> {

}
