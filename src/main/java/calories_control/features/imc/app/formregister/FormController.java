package calories_control.features.imc.app.formregister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import calories_control.features.imc.app.getimcwhituser.IGetImcWhitUser;

@Controller
@RequestMapping("/imc/formRegister")
public class FormController {

    private static final Logger logger = LoggerFactory.getLogger(FormController.class);

    private final IGetImcWhitUser imcWhitUser;

    public FormController(IGetImcWhitUser imcWhitUser) {
        this.imcWhitUser = imcWhitUser;
    }

    @GetMapping
    public String showRegisterForm(Model model,
            @RequestParam(value = "mensaje", required = false) String mensaje,
            @RequestParam(value = "error", required = false) String error) {
        try {
            model.addAttribute("content", "fragments/principal/imc-register");
            model.addAttribute("registros", imcWhitUser.findImcWhitUser());
        } catch (Exception e) {
            logger.error("Error fetching IMC with user data", e);
            throw e; // vuelve a lanzar para que el test lo capture
        }

        if (mensaje != null)
            model.addAttribute("mensaje", mensaje);
        if (error != null)
            model.addAttribute("error", error);

        return "index";
    }
}
