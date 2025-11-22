package calories_control.features.auth.domain;

import java.util.Optional;

import calories_control.features.auth.infra.AvatarModel;

public interface IAvatarRepository {

    public Optional<AvatarModel> updateAvatar(Long userId, String avatarUrl);

    public Optional<AvatarModel> getAvatar(Long userId);
}
