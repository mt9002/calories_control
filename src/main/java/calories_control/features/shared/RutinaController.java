package calories_control.features.shared;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutinaController {

    @GetMapping("/rutina")
    public String getMethodName(Model model) {
        model.addAttribute("content", "fragments/principal/work-out");
        return "index";
    }

}
