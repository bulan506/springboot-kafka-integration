package com.gringottsbank.events;

import com.gringottsbank.model.AddressChangeData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddressChangedEvent extends Event<AddressChangeData> {}

