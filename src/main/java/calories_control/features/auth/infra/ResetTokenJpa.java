package calories_control.features.auth.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ResetTokenJpa extends JpaRepository<PasswordResetToken, Long>{
    Optional<PasswordResetToken> findByToken(String token);
}
