package calories_control.features.IMC.getByAll;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imc")
public class GetAllController {

    private final GetAllUseCase getAllUseCase;

    public GetAllController(GetAllUseCase getAllUseCase) {
        this.getAllUseCase = getAllUseCase;
    }

    @GetMapping("/tables")
    public String getAll(Model model) {
        model.addAttribute("imc", getAllUseCase.getAll());
        return "tables";
    }

}
