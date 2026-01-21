package calories_control.auth.infra;

import java.util.Optional;

import calories_control.user.infra.avatar.AvatarJpa;
import calories_control.user.infra.user.UserJpa;
import calories_control.user.infra.user.UserModel;
import org.springframework.stereotype.Repository;

import calories_control.auth.domain.IAuthRepository;

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
