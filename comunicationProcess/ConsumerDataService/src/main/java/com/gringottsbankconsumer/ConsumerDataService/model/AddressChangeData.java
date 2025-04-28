package com.gringottsbankconsumer.ConsumerDataService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressChangeData {
    private String clientId;
    private Address newAddress;
    private Address oldAddress;
}