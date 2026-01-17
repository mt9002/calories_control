package calories_control.features.auth.infra.user;

import java.util.Optional;

import calories_control.features.auth.infra.security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}
