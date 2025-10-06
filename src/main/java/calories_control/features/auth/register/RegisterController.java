package calories_control.features.auth.register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import calories_control.features.auth.Register;
import calories_control.features.shared.Result;
import calories_control.features.shared.State;

@Controller
@RequestMapping("/auth/register")
public class RegisterController {

    private final IRegisterAuthUserCase registerAuthUserCase;

    public RegisterController(IRegisterAuthUserCase registerAuthUserCase) {
        this.registerAuthUserCase = registerAuthUserCase;
    }

    @GetMapping
    public String showRegistrationForm(Model model,
            @RequestParam(value = "mensaje", required = false) String mensaje,
            @RequestParam(value = "error", required = false) String error) {

        if (mensaje != null) {
            model.addAttribute("mensaje", mensaje);
            return "login";
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("register", new Register());
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute Register register, RedirectAttributes redirect) {
        if (!register.getPassword().equals(register.getConfirmPassword())) {
            redirect.addAttribute("error", "Las contraseñas no coinciden");
            return "register"; // Vuelve al formulario
        }
        Result result = registerAuthUserCase.register(register);
        if (result.getState() == State.SUCCESS) {
                redirect.addAttribute("mensaje", result.getMessage());
                
            } else {
                redirect.addAttribute("error", result.getMessage());
            }
        
        return "redirect:/auth/register";
    }
}
