package calories_control.user.infra.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}
