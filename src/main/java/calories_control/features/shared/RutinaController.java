package calories_control.features.shared;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import calories_control.features.auth.infra.security.util.SecurityContextUtil;

@Controller
public class RutinaController {

    @GetMapping("/rutina")
    public String getMethodName(Model model) {
        String userName = SecurityContextUtil.getUser().getName();
        model.addAttribute("content", "fragments/principal/work-out");
        model.addAttribute("userName", userName);
        return "index";
    }

}
