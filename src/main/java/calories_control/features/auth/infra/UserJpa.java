package calories_control.features.auth.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);
    
}
