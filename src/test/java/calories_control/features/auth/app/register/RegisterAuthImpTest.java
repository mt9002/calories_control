package calories_control.features.auth.app.register;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import calories_control.features.auth.domain.IAuthRepository;
import calories_control.features.auth.domain.User;
import calories_control.features.auth.infra.user.Role;
import calories_control.features.shared.Result;
import calories_control.features.shared.State;

class RegisterAuthImpTest {

    @Mock
    private IAuthRepository authRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterAuthImp registerAuthImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() {
        // Arrange
        RequestRegisterDto registerDto = new RequestRegisterDto("John Doe", "john@example.com", "password");
        String hashedPassword = "hashedPassword";
        User user = new User("John Doe", "john@example.com", hashedPassword);
        user.setRole(Role.USER);

        when(passwordEncoder.encode("password")).thenReturn(hashedPassword);
        when(authRepository.register(any(User.class))).thenReturn(Optional.of(user));

        // Act
        Result result = registerAuthImp.execute(registerDto);

        // Assert
        assertEquals(State.SUCCESS, result.getState());
        assertEquals("User registered successfully", result.getMessage());
        verify(passwordEncoder, times(1)).encode("password");
        verify(authRepository, times(1)).register(any(User.class));
    }

    @Test
    void testExecute_Failure() {
        // Arrange
        RequestRegisterDto registerDto = new RequestRegisterDto("John Doe", "john@example.com", "password");
        String hashedPassword = "hashedPassword";

        when(passwordEncoder.encode("password")).thenReturn(hashedPassword);
        when(authRepository.register(any(User.class))).thenReturn(Optional.empty());

        // Act
        Result result = registerAuthImp.execute(registerDto);

        // Assert
        assertEquals(State.NOT_FOUND, result.getState());
        assertEquals("error", result.getMessage());
        verify(passwordEncoder, times(1)).encode("password");
        verify(authRepository, times(1)).register(any(User.class));
    }
}