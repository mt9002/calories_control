package calories_control.features.auth.app.register;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import calories_control.features.auth.domain.IAuthRepository;
import calories_control.features.auth.domain.User;
import calories_control.features.auth.infra.Role;
import calories_control.features.shared.Result;
import calories_control.features.shared.State;

@Component
public class RegisterAuthImp implements IRegisterAuthUserCase {

    private final IAuthRepository registerAuthRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterAuthImp(
            IAuthRepository registerAuthRepository,
            PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
        this.registerAuthRepository = registerAuthRepository;
    }

    @Override
    public Result execute(RequestRegisterDto register) {
        String hash = passwordEncoder.encode(register.getPassword());

        User user = new User(
                register.getName(),
                register.getEmail(),
                hash);
        user.setRole(Role.valueOf("USER"));

        if (registerAuthRepository.register(user).isPresent()) {
            return Result.success("User registered successfully");
        }

        return Result.failure("error", State.NOT_FOUND);
    }

}
