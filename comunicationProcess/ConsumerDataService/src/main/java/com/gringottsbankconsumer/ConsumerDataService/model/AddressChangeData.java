package com.gringottsbankconsumer.ConsumerDataService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressChangeData {
    private String clientId;
    private List<Address> newAddress;
}