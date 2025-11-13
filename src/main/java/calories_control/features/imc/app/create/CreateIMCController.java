package calories_control.features.imc.app.create;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import calories_control.features.shared.State;

@Controller
@RequestMapping("/imc")
public class CreateIMCController {
    private final ICreateIMCUseCase createIMCUseCase;

    public CreateIMCController(ICreateIMCUseCase createIMCUseCase) {
        this.createIMCUseCase = createIMCUseCase;

    }

    @PostMapping("/create")
    public String createIMC(
            @RequestParam("peso") double peso,
            @RequestParam("altura") double altura,
            RedirectAttributes redirect) {

        var result = createIMCUseCase.add(peso, altura);

        if (result.getState() == State.SUCCESS) {
            redirect.addFlashAttribute("mensaje", result.getMessage());
        } else {
            redirect.addAttribute("error", result.getMessage());
        }

        return "redirect:/imc/formRegister";
    }
}
