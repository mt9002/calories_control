package calories_control.features.auth.infra;

import java.util.Optional;



import calories_control.features.auth.domain.IAvatarRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AvatarRepository implements IAvatarRepository {

    private final AvatarJpa avatarJpa;

    public AvatarRepository(AvatarJpa avatarJpa) {
        this.avatarJpa = avatarJpa;
    }

    @Override
    public Optional<AvatarModel> updateAvatar(Long userId, String avatarUrl) {
        Optional<AvatarModel> existingAvatar = avatarJpa.findByUserId(userId);
        AvatarModel avatar;
        if (existingAvatar.isPresent()) {
            avatar = existingAvatar.get();
            avatar.setAvatarUrl(avatarUrl);
        } else {
            avatar = new AvatarModel(userId, avatarUrl);
        }
        AvatarModel savedAvatar = avatarJpa.save(avatar);
        return Optional.of(savedAvatar);
    }

    @Override
    public Optional<AvatarModel> getAvatar(Long userId) {
        return avatarJpa.findByUserId(userId);
    }
}
