package project.personal.personalstoremanagementproject.repositories;

import org.springframework.stereotype.Repository;
import project.personal.personalstoremanagementproject.entities.UserAccount;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
    boolean existsByEmailOrUsernameAndIsActiveTrue(String email, String userName);
    Optional<UserAccount> findByUsernameAndIsActiveTrue(String userName);
    Optional<UserAccount> findByUsernameAndIsActiveFalse(String userName);
    Optional<UserAccount> findByUsernameOrEmailOrPhoneNumber(String input);

}
