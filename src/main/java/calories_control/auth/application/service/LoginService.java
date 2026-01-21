package calories_control.auth.application.service;

import calories_control.auth.application.dtos.LoginRequest;
import calories_control.auth.application.dtos.LoginResponse;
import calories_control.auth.exception.AuthenticationdException;
import calories_control.auth.exception.LoginNotFoundException;
import calories_control.user.infra.user.UserModel;
import calories_control.auth.infra.security.jwt.IJWT;
import calories_control.auth.exception.AuthenticationNotFound;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoginService {

    private final AuthenticationManager authenticateUser;
    private final IJWT jwt;

    public LoginService(AuthenticationManager authenticateUser, IJWT jwt) {
        this.authenticateUser = authenticateUser;
        this.jwt = jwt;
    }

    public LoginResponse login(LoginRequest loginRequest) {

        String email = loginRequest.email();
        String password = loginRequest.password();

        if (email == null || password == null){
            throw new LoginNotFoundException("email o password no pueden ser vacios");
        }

        Authentication authentication = this.authenticationManager(email, password);

        if (!authentication.isAuthenticated() || Objects.equals(authentication.getPrincipal(), "anonymousUser")) {
            throw new AuthenticationNotFound("Usuario no autorizado");
        }

        UserModel userModel = (UserModel) authentication.getPrincipal();
        String token = jwt.getToken(userModel);

        // Guarda la autenticacion en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (userModel == null) {
            throw new AuthenticationdException("UserModel es null después de la autenticación");
        }

        return new LoginResponse(token, userModel.getName());

    }

    private Authentication authenticationManager(String email, String password) {
        return authenticateUser.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
    }

}
