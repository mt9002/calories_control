package calories_control.features.auth;

import java.util.Optional;

import calories_control.features.auth.zpersistence.PasswordResetToken;
import calories_control.features.auth.zpersistence.UserModel;

public interface IAuthRepository {

    public Optional<User> register(User user);
    public Optional<UserModel> findByEmail(String email);
    public Optional<PasswordResetToken> saveResetToken(PasswordResetToken passwordResetToken);
}
