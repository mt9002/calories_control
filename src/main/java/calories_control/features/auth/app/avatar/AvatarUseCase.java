package calories_control.features.auth.app.avatar;

import java.util.Optional;

import org.springframework.stereotype.Service;

import calories_control.features.auth.domain.IAuthRepository;
import calories_control.features.auth.infra.AvatarModel;
import calories_control.features.shared.Result;

@Service
public class AvatarUseCase {

    private final IAuthRepository authRepository;

    public AvatarUseCase(IAuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Result<AvatarModel> updateAvatar(Long userId, String avatarUrl) {
        Optional<AvatarModel> avatar = authRepository.updateAvatar(userId, avatarUrl);
        if (avatar.isPresent()) {
            return Result.success("Avatar updated successfully.", avatar.get());
        }
        return Result.failure("Avatar update failed", null);
    }
}
