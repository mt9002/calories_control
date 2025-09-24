package calories_control.features.auth;

import java.util.Optional;

public interface IAuthRepository {

    public Optional<User> register(User user);
    
}
