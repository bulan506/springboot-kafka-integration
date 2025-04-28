package com.gringottsbank.events;

import com.gringottsbank.model.Client;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientSavedEvent extends Event<Client> {}
