package calories_control.features.auth.infra.security.jwt;

import io.jsonwebtoken.Claims;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;


public interface IJWT {
    String getToken(UserDetails user);
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    <T> T getClaim(String token, Function<Claims, T> claimsResolver);
}
