package user.user_service.Repository;

import user.user_service.Entity.roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<roles, Long> {
}
