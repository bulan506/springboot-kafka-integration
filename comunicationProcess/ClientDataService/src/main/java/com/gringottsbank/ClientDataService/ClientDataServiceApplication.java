package com.gringottsbank.ClientDataService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("com.gringottsbank")
@EnableMongoRepositories("com.gringottsbank.repository")
@EntityScan(basePackages = "com.gringottsbank.model")
public class ClientDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientDataServiceApplication.class, args);
	}

}