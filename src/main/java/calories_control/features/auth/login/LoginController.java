package calories_control.features.auth.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import calories_control.features.shared.Result;
import calories_control.features.shared.State;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/auth/login")
public class LoginController {

    private final ILoginUseCase loginUseCase;

    public LoginController(ILoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @GetMapping
    public String login(
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "error", required = false) String error,
            Model model) {
        System.out.println("Entrando a login get: " );
        if (logout != null) {
            model.addAttribute("mensaje", "Sesión cerrada correctamente.");
        }
        if (error != null) {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
        }
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password, RedirectAttributes redirect) {
        System.out.println("Entrando a login post: " + email);
        Result result = loginUseCase.login(email, password);
        if (result.getState() == State.SUCCESS) {
            return "redirect:/home";
        } else {
            redirect.addAttribute("error", result.getMessage());
        }
        return "redirect:/auth/login";
    }

}
