package calories_control.features.auth.domain;

import java.util.Optional;

import calories_control.features.auth.infra.security.PasswordResetToken;
import calories_control.features.auth.infra.user.UserModel;

public interface IAuthRepository {

    public UserModel save(UserModel userModel);

    public Optional<UserModel> findByEmail(String email);
}

// Reason: easy testing - mock ### investment in dependencies #### easy change of ORM -- Service does not depend on: jpa