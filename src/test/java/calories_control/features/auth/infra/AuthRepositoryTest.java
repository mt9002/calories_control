package calories_control.features.auth.infra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import calories_control.features.auth.domain.User;

class AuthRepositoryTest {

    @Mock
    private UserJpa userJpa;

    @Mock
    private ResetTokenJpa resetTokenJpa;

    @InjectMocks
    private AuthRepository authRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Arrange
        User user = new User("John Doe", "john@example.com", "password");
        user.setRole(Role.USER);
        UserModel userModel = new UserModel("John Doe", "john@example.com", "password");
        userModel.setId(1L);
        userModel.setRole(Role.USER);

        when(userJpa.save(any(UserModel.class))).thenReturn(userModel);

        // Act
        Optional<User> result = authRepository.register(user);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(userJpa, times(1)).save(any(UserModel.class));
    }

    @Test
    void testFindByEmail() {
        // Arrange
        String email = "john@example.com";
        UserModel userModel = new UserModel("John Doe", email, "password");

        when(userJpa.findByEmail(email)).thenReturn(Optional.of(userModel));

        // Act
        Optional<UserModel> result = authRepository.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        verify(userJpa, times(1)).findByEmail(email);
    }

    @Test
    void testSaveResetToken() {
        // Arrange
        PasswordResetToken token = new PasswordResetToken();
        PasswordResetToken savedToken = new PasswordResetToken();
        savedToken.setId(1L);

        when(resetTokenJpa.save(token)).thenReturn(savedToken);

        // Act
        Optional<PasswordResetToken> result = authRepository.saveResetToken(token);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(resetTokenJpa, times(1)).save(token);
    }
}