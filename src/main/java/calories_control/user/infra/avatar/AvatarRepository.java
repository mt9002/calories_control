package calories_control.user.infra.avatar;

import java.util.Optional;



import calories_control.auth.domain.IAvatarRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AvatarRepository implements IAvatarRepository {

    private final AvatarJpa avatarJpa;

    public AvatarRepository(AvatarJpa avatarJpa) {
        this.avatarJpa = avatarJpa;
    }

    @Override
    public AvatarModel updateAvatar(AvatarModel avatarModel) {
        return avatarJpa.save(avatarModel);
    }

    @Override
    public Optional<AvatarModel> getAvatar(Long userId) {
        return avatarJpa.findByUserId(userId);
    }
}
