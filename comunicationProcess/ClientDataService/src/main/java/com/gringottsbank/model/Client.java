package com.gringottsbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Clients_General")
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
    private List<Address> addresses;
    private String lastModified;
    private String lastEventId;
}
