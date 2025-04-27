package com.gringottsbankconsumer.ConsumerDataService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Clients")
public class Client {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String accountNumber;
    private String accountType;
    private String accountStatus;
    private String addresses;
    private String data;
    private String flooNetworkId;

}
