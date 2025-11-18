package calories_control.features.auth.app.profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import calories_control.features.auth.infra.UserModel;
import calories_control.features.auth.infra.security.util.SecurityContextUtil;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("content", "fragments/principal/profile");

        UserModel user = SecurityContextUtil.getUser();
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userRole", user.getRole());
        model.addAttribute("fechaRegistro", user.getFechaRegistro());
        model.addAttribute("userId", user.getId());

        return "index";
    }
}