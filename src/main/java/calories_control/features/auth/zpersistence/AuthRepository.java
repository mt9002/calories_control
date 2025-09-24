package calories_control.features.auth.zpersistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import calories_control.features.auth.IAuthRepository;
import calories_control.features.auth.User;

@Repository
public class AuthRepository implements IAuthRepository {
    

    private final UserJpa userJpa;

    public AuthRepository(UserJpa userJpa) {
        this.userJpa = userJpa;
    }
    
    @Override
    public Optional<User> register(User user) {
        UserModel userModel = new UserModel(user.getName(), user.getEmail(), user.getPassword());
        userModel.setRole(user.getRole());
        UserModel userResult = userJpa.save(userModel);
        if (userResult == null) {
            return Optional.empty();
            
        }
        user.setId(userResult.getId());

        return Optional.of(user);
    }
}
