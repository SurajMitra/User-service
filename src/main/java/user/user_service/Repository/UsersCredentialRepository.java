package user.user_service.Repository;

import user.user_service.Entity.users_credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersCredentialRepository extends JpaRepository<users_credentials, Long> {

    Optional<users_credentials> findByUser_Email(String email);

}
