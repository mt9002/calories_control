package calories_control.features.auth.register;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import calories_control.features.auth.IAuthRepository;
import calories_control.features.auth.Register;
import calories_control.features.auth.User;
import calories_control.features.auth.security.IJWT;
import calories_control.features.auth.zpersistence.Role;
import calories_control.features.shared.Result;
import calories_control.features.shared.State;

@Component
public class RegisterAuth implements IRegisterAuthUserCase {

    private final IAuthRepository registerAuthRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterAuth(
            IAuthRepository registerAuthRepository,
            PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
        this.registerAuthRepository = registerAuthRepository;
    }

    @Override
    public Result register(Register register) {
        String hash = passwordEncoder.encode(register.getPassword());
        User user = new User(
                register.getName(),
                register.getEmail(),
                hash);
        user.setRole(Role.valueOf("USER"));
        if (registerAuthRepository.register(user).isPresent()) {
            return Result.success("User registered successfully");
        }
        registerAuthRepository.register(user);
        return Result.failure("error", State.NOT_FOUND);
    }

}
