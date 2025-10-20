package calories_control.features.auth.passwordchange;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import calories_control.features.auth.IAuthRepository;
import calories_control.features.auth.infra.PasswordResetToken;
import calories_control.features.auth.infra.UserModel;
import calories_control.features.sendgrid.IEmailService;

@Component
public class EmailRecoveryUseCase {

    private final IAuthRepository authRepository;
    private final IEmailService emailService;

    public EmailRecoveryUseCase(IAuthRepository authRepository, IEmailService emailService) {
        this.authRepository = authRepository;
        this.emailService = emailService;
    }

    public void sendTokenEmilRecovery(String email) {
        Optional<UserModel> user = authRepository.findByEmail(email);
        if (user.isEmpty())
            return;

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setExpiration(LocalDateTime.now().minusMinutes(15));
        resetToken.setUser(user.get());
        authRepository.saveResetToken(resetToken);

        String link = "http://localhost:8080/reset-password?token=" + token;
        System.out.println(link);
        emailService.sendEmail("mauricio.tovar.gil@gmail.com", "Restablece tu contraseña", "Haz clic: " + link);
    }

}
