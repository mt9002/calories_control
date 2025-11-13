package calories_control.features.auth.app.resetpassword;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import calories_control.features.auth.domain.IAuthRepository;
import calories_control.features.auth.domain.User;
import calories_control.features.auth.infra.PasswordResetToken;
import calories_control.features.auth.infra.ResetTokenJpa;
import calories_control.features.auth.infra.UserModel;

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
