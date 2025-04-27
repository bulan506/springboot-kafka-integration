package com.gringottsbankconsumer.ConsumerDataService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ConsumerDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerDataServiceApplication.class, args);
	}

}
