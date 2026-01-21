package calories_control.auth.application.service;

import calories_control.auth.domain.IAuthMapper;
import calories_control.auth.application.dtos.RegisterRequest;
import calories_control.auth.application.dtos.RegisterResponse;
import calories_control.user.infra.user.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import calories_control.auth.domain.IAuthRepository;
import calories_control.user.domain.User;
import calories_control.user.infra.user.Role;

@Component
public class RegisterService {

    private final IAuthRepository registerAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final IAuthMapper authMapper;

    public RegisterService(
            IAuthRepository registerAuthRepository,
            PasswordEncoder passwordEncoder, IAuthMapper authMapper) {

        this.passwordEncoder = passwordEncoder;
        this.registerAuthRepository = registerAuthRepository;
        this.authMapper = authMapper;
    }

    public RegisterResponse register(RegisterRequest registerRequest) {



        String hash = passwordEncoder.encode(registerRequest.getPassword());
        Role roleDefault = Role.valueOf("USER");

        User user = new User(
                registerRequest.getName(),
                registerRequest.getEmail(),
                hash,
                roleDefault);

        UserModel userModel = registerAuthRepository.save(authMapper.toUserModel(user));

        return authMapper.toRegisterResponse(userModel);
    }
}
