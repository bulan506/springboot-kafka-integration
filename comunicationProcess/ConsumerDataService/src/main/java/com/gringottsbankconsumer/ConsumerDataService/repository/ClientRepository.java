package com.gringottsbankconsumer.ConsumerDataService.repository;

import com.gringottsbankconsumer.ConsumerDataService.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

}
