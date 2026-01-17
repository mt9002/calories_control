package calories_control.features.auth.domain;

import java.util.Optional;

import calories_control.features.auth.infra.avatar.AvatarModel;

public interface IAvatarRepository {

    AvatarModel updateAvatar(AvatarModel avatarModel);
    Optional<AvatarModel> getAvatar(Long userId);
}
