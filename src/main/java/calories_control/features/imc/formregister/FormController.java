package calories_control.features.imc.formregister;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import calories_control.features.imc.create.CreateIMCUseCase;
import calories_control.features.imc.create.ICreateIMCUseCase;
import calories_control.features.imc.getbyuser.IGetByUser;
import calories_control.features.imc.zpersistence.IMCWithUserProjection;
import calories_control.features.shared.State;

@Controller
@RequestMapping("/imc/create")
public class FormController {

    private final ICreateIMCUseCase createIMCUseCase;
    private final IGetByUser getAllUseCase;

    public FormController(CreateIMCUseCase createIMCUseCase, IGetByUser getAllUseCase) {
        this.createIMCUseCase = createIMCUseCase;
        this.getAllUseCase = getAllUseCase;
    }

    @GetMapping
    public String showRegisterForm(
            Model model,
            @RequestParam(value = "mensaje", required = false) String mensaje,
            @RequestParam(value = "error", required = false) String error) {

        List<IMCWithUserProjection> registros = getAllUseCase.getByUser();

        model.addAttribute("registros", registros);

        if (mensaje != null) {
            model.addAttribute("mensaje", mensaje);
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "imc-register";
    }

    @PostMapping
    public String createIMC(
            @RequestParam("peso") double peso,
            @RequestParam("altura") double altura,
            RedirectAttributes redirect) {

        try {
            var result = createIMCUseCase.add(peso, altura);

            if (result.getState() == State.SUCCESS) {
                redirect.addAttribute("mensaje", result.getMessage());
            } else {
                redirect.addAttribute("error", result.getMessage());
            }

        } catch (Exception e) {
            redirect.addAttribute("error", "xxx");
        }
        return "redirect:/imc/create";
    }
}
