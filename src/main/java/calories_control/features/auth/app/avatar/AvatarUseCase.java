package calories_control.features.auth.app.avatar;

import java.util.Optional;

import org.springframework.stereotype.Service;

import calories_control.features.auth.domain.IAvatarRepository;
import calories_control.features.auth.infra.AvatarModel;
import calories_control.features.shared.Result;

@Service
public class AvatarUseCase {

    private final IAvatarRepository avatarRepository;

    public AvatarUseCase(IAvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Result<AvatarModel> updateAvatar(Long userId, String avatarUrl) {
        Optional<AvatarModel> avatar = avatarRepository.updateAvatar(userId, avatarUrl);
        if (avatar.isPresent()) {
            return Result.success("Avatar updated successfully.", avatar.get());
        }
        return Result.failure("Avatar update failed", null);
    }
}
