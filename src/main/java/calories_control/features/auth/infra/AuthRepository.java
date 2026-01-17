package calories_control.features.auth.infra;

import java.util.Optional;

import calories_control.features.auth.infra.avatar.AvatarJpa;
import calories_control.features.auth.infra.security.PasswordResetToken;
import calories_control.features.auth.infra.security.ResetTokenJpa;
import calories_control.features.auth.infra.user.UserJpa;
import calories_control.features.auth.infra.user.UserModel;
import org.springframework.stereotype.Repository;

import calories_control.features.auth.domain.IAuthRepository;
import calories_control.features.auth.domain.User;

@Repository
public class AuthRepository implements IAuthRepository {

    private final UserJpa userJpa;

    public AuthRepository(UserJpa userJpa, AvatarJpa avatarJpa) {
        this.userJpa = userJpa;
    }

    @Override
    public UserModel save(UserModel userModel) {
        return userJpa.save(userModel);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userJpa.findByEmail(email);
    }

}
