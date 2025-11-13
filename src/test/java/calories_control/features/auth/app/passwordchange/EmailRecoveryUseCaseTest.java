package calories_control.features.auth.app.passwordchange;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import calories_control.features.auth.domain.IAuthRepository;
import calories_control.features.auth.infra.PasswordResetToken;
import calories_control.features.auth.infra.UserModel;
import calories_control.features.sendgrid.IEmailService;

class EmailRecoveryUseCaseTest {

    @Mock
    private IAuthRepository authRepository;

    @Mock
    private IEmailService emailService;

    @InjectMocks
    private EmailRecoveryUseCase emailRecoveryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendTokenEmilRecovery_UserExists() {
        // Arrange
        String email = "test@example.com";
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setEmail(email);

        when(authRepository.findByEmail(email)).thenReturn(Optional.of(userModel));
        when(authRepository.saveResetToken(any(PasswordResetToken.class)))
                .thenReturn(Optional.of(new PasswordResetToken()));

        // Act
        emailRecoveryUseCase.sendTokenEmilRecovery(email);

        // Assert
        verify(authRepository, times(1)).findByEmail(email);
        verify(authRepository, times(1)).saveResetToken(any(PasswordResetToken.class));
        verify(emailService, times(1)).sendEmail(eq("mauricio.tovar.gil@gmail.com"), eq("Restablece tu contraseña"),
                anyString());
    }

    @Test
    void testSendTokenEmilRecovery_UserNotExists() {
        // Arrange
        String email = "test@example.com";

        when(authRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        emailRecoveryUseCase.sendTokenEmilRecovery(email);

        // Assert
        verify(authRepository, times(1)).findByEmail(email);
        verify(authRepository, never()).saveResetToken(any(PasswordResetToken.class));
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }
}