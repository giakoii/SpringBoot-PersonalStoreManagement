package project.personal.personalstoremanagementproject.repositories;

import org.springframework.stereotype.Repository;
import project.personal.personalstoremanagementproject.entities.ViewUserCustomer;

@Repository
public interface ViewUserInformationRepository extends BaseRepository<ViewUserCustomer, Long> {
}
