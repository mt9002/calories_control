package calories_control.features.auth.app.login;

import calories_control.features.auth.infra.user.UserModel;
import calories_control.features.auth.infra.security.jwt.IJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

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

        Authentication authentication = this.authenticationManager(email, password);

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            System.out.println("err");
        }

        UserModel userModel = (UserModel) authentication.getPrincipal();
        String token = jwt.getToken(userModel);

        // Guarda la autenticacion en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new LoginResponse(token, userModel.getName());
    }

    private Authentication authenticationManager(String email, String password) {
        return authenticateUser.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
    }

}
