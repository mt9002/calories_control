package calories_control.features.imc.create;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import calories_control.features.shared.State;

@Controller
@RequestMapping("/imc")
public class CreateIMCController {
    private final ICreateIMCUseCase createIMCUseCase;

    public CreateIMCController(ICreateIMCUseCase createIMCUseCase) {
        this.createIMCUseCase = createIMCUseCase;

    }

    @PostMapping("/createIMC")
    public String createIMC(
            @RequestParam("peso") double peso,
            @RequestParam("altura") double altura) {

        try {
            var result = createIMCUseCase.add(peso, altura);

            if (result.getState() == State.SUCCESS) {
                return "IMC creado exitosamente";
            }

        } catch (Exception e) {
            System.out.println("Error al crear el IMC: " + e.getMessage());

        }
        return "home";
    }
}
