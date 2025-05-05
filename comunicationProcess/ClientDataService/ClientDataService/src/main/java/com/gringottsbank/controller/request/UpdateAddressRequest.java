package com.gringottsbank.controller.request;

import com.gringottsbank.model.Address;
import lombok.Data;
import java.util.List;

@Data
public class UpdateAddressRequest {
    private String clientId;
    private List<Address> newAddresses;
}