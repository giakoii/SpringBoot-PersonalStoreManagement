package project.personal.personalstoremanagementproject.repositories;

import org.springframework.stereotype.Repository;
import project.personal.personalstoremanagementproject.entities.UserAccount;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserAccount, Long> {
    boolean existsByEmailOrUsernameAndIsActiveTrue(String email, String userName);
    UserAccount findByUsernameAndIsActiveTrue(String userName);
    Optional<UserAccount> findByUsernameAndIsActiveFalse(String userName);
}
