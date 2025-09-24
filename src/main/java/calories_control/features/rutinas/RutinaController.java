package calories_control.features.rutinas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class RutinaController {
    
    @GetMapping("/rutina")
    public String getMethodName() {
        return "rutina";
    }
    
}
