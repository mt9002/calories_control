package calories_control.features.auth.app.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import calories_control.features.shared.Result;
import calories_control.features.shared.State;

@Component
public class LoginUseCase implements ILoginUseCase {

    private final AuthenticationManager authenticateUser;

    public LoginUseCase(
            AuthenticationManager authenticateUser) {

        this.authenticateUser = authenticateUser;
    }

    @Override
    public Result login(String email, String password) {

        Authentication authentication = this.authenticationManager(email, password);

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {

            return Result.failure("login failed", State.UNAUTHORIZED);
        }

        // Guarda la autenticacion en el contexto de seguridad
        // (HttpSessionSecurityContextRepositor)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return Result.success("login success ");
    }

    private Authentication authenticationManager(String email, String password) {
        return authenticateUser.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
    }

}
