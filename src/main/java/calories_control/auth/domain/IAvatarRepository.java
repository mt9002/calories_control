package calories_control.auth.domain;

import java.util.Optional;

import calories_control.user.infra.avatar.AvatarModel;

public interface IAvatarRepository {

    AvatarModel updateAvatar(AvatarModel avatarModel);
    Optional<AvatarModel> getAvatar(Long userId);
}
