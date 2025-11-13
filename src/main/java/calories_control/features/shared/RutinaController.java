package calories_control.features.shared;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutinaController {

    @GetMapping("/rutina")
    public String getMethodName() {
        return "rutina";
    }

}
