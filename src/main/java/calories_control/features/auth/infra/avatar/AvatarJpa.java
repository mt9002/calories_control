package calories_control.features.auth.infra.avatar;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarJpa extends JpaRepository<AvatarModel, Long> {
    Optional<AvatarModel> findByUserId(Long userId);
}
