package project.personal.personalstoremanagementproject.repositories;

import org.springframework.stereotype.Repository;
import project.personal.personalstoremanagementproject.entities.Customer;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {
}
