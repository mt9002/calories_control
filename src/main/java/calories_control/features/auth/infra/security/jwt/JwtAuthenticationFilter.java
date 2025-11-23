package calories_control.features.auth.infra.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;

import calories_control.features.shared.Result;
import calories_control.features.shared.State;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWT jwt;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
        this.jwt = new JWT();
        this.userDetailsService = userDetailsService;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        final String username;
        final String requestUri = request.getRequestURI();

        try {
            if (token != null) {
                username = jwt.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwt.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }

                if (isUserUri(requestUri) && !hasRole("ROLE_USER")) {
                    validAcces(response);
                    return;
                }

            }
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ResponseEntity<Result<String>> resp = ResponseEntity
                    .status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(Result.failure("Token has expired ", State.UNAUTHORIZED));
            String json = objectMapper.writeValueAsString(resp);
            response.getWriter().write(json);
            logger.warn("Token has expired: {}" + e.getMessage());
            return;
        } catch (MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ResponseEntity<Result<String>> resp = ResponseEntity
                    .status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(Result.failure("Invalid token", State.UNAUTHORIZED));
            String json = objectMapper.writeValueAsString(resp);
            response.getWriter().write(json);
            logger.warn("Invalid token: {}" + e.getMessage());
            return;
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ResponseEntity<Result<String>> resp = ResponseEntity
                    .status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(Result.failure("JWT validity cannot be asserted and should not be trusted.",
                            State.UNAUTHORIZED));
            String json = objectMapper.writeValueAsString(resp);
            response.getWriter().write(json);
            logger.warn("JWT inválido: {}" + e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);

    }

    private String getTokenFromRequest(HttpServletRequest request) {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // reemplaza el bearer con un str vacio.
        }

        return null;
    }

    private boolean hasRole(String role) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false; // Si no hay autenticación, no tiene roles
        }
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
        return isAdmin;
    }

    private boolean isUserUri(String requestUri) {
        // Lista de las URIs que deseas verificar
        List<String> protectedUris = List.of(
                "/calculadora/operaciones");
        // Verificar si alguna URI de la lista está contenida en requestUri
        return protectedUris.stream().anyMatch(requestUri::contains);
    }

    public void validAcces(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResponseEntity<Result<String>> resp = ResponseEntity
                .status(HttpServletResponse.SC_FORBIDDEN)
                .body(
                        Result.failure("No tienes permiso para acceder a este recurso.", State.UNAUTHORIZED));
        String json = objectMapper.writeValueAsString(resp);
        response.getWriter().write(json);
    }
}
