package calories_control.features.auth.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth/login")
public class LoginController {

    @GetMapping
    public String login(
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "error", required = false) String error,
            Model model) {

        if (logout != null) {
            model.addAttribute("mensaje", "Sesión cerrada correctamente.");
        }
        if (error != null) {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
        }
        return "login";
    }
}
