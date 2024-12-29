package project.personal.personalstoremanagementproject.repositories;
import org.springframework.stereotype.Repository;
import project.personal.personalstoremanagementproject.entities.Address;
import java.util.Optional;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {
    Optional<Address> findByUserId(Long userId);
}
