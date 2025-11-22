package calories_control.features.auth.infra;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import calories_control.features.auth.domain.IAuthRepository;
import calories_control.features.auth.domain.User;

@Repository
public class AuthRepository implements IAuthRepository {

    private final UserJpa userJpa;

    private final ResetTokenJpa resetTokenJpa;

    public AuthRepository(UserJpa userJpa, ResetTokenJpa resetTokenJpa, AvatarJpa avatarJpa) {
        this.userJpa = userJpa;

        this.resetTokenJpa = resetTokenJpa;
    }

    @Override
    public Optional<User> register(User user) {
        UserModel userModel = new UserModel(user.getName(), user.getEmail(), user.getPassword());
        userModel.setRole(user.getRole());
        UserModel userResult = userJpa.save(userModel);

        user.setId(userResult.getId());

        return Optional.of(user);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userJpa.findByEmail(email);
    }

    @Override
    public Optional<PasswordResetToken> saveResetToken(PasswordResetToken passwordResetToken) {
        PasswordResetToken pr = resetTokenJpa.save(passwordResetToken);
        return Optional.of(pr);

    }

}
