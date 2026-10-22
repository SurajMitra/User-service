package user.user_service.Repository;

import user.user_service.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<users, Long> {

    Optional<users> findByEmail(String email);
    boolean existsByEmail(String email);
}
