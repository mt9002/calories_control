package calories_control.features.auth.app.resetpassword;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import calories_control.features.auth.domain.IAuthRepository;
import calories_control.features.auth.domain.User;
import calories_control.features.auth.infra.PasswordResetToken;
import calories_control.features.auth.infra.ResetTokenJpa;
import calories_control.features.auth.infra.UserModel;

@Controller
public class ResetController {

    private final ResetTokenJpa resetTokenJpa;
    private final PasswordEncoder passwordEncoder;
    private final IAuthRepository authRepository;

    public ResetController(ResetTokenJpa resetTokenJpa, PasswordEncoder passwordEncoder,
            IAuthRepository authRepository) {
        this.resetTokenJpa = resetTokenJpa;
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;

    }

    @GetMapping("/auth/email/recovery")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/auth/email/recovery")
    public String processForgotPassword(@RequestParam String email, Model model) {
        Optional<UserModel> userOpt = authRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            model.addAttribute("error", "No se encontró una cuenta con ese correo electrónico.");
            return "forgot-password";
        }

        // Generate reset token
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(userOpt.get());
        resetToken.setExpiration(LocalDateTime.now().plusHours(24)); // Token valid for 24 hours

        authRepository.saveResetToken(resetToken);

        // For demo purposes, show the reset link instead of sending email
        String resetLink = "http://localhost:8080/reset-password?token=" + token;
        model.addAttribute("message",
                "Se ha enviado un enlace de recuperación a tu correo electrónico. Para fines de demostración, el enlace es: "
                        + resetLink);
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        Optional<PasswordResetToken> tokenOpt = resetTokenJpa.findByToken(token);

        if (tokenOpt.isEmpty() || tokenOpt.get().getExpiration().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "El token es inválido o ha expirado.");
            return "reset-password";
        }

        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String chagenPassword(@RequestParam String token,
            @RequestParam String password,
            Model model) {

        Optional<PasswordResetToken> tokenOpt = resetTokenJpa.findByToken(token);

        if (tokenOpt.isEmpty() || tokenOpt.get().getExpiration().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "El token es inválido o ha expirado.");
            return "reset-password";
        }

        UserModel userModel = tokenOpt.get().getUser();
        userModel.setPassword(passwordEncoder.encode(password));
        User user = new User();
        user.setId(userModel.getId());
        user.setPassword(password);
        authRepository.register(user);

        resetTokenJpa.delete(tokenOpt.get()); // elimina token usado

        model.addAttribute("message", "Contraseña cambiada correctamente.");
        return "reset-password";
    }

}
