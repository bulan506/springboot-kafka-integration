package ucr.ac.cr.consumer.service.repository;

import ucr.ac.cr.consumer.service.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}