package calories_control.features.auth.passwordchange;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/auth/email/recovery")
public class EmailRecoveryController {
    
    private final EmailRecoveryUseCase emailRecovery;

    public EmailRecoveryController(EmailRecoveryUseCase emailRecovery){
        this.emailRecovery = emailRecovery;
        
    }
    
    @GetMapping
    public String formSendEmail(){
        return "forgot-password";
    }
 
    @PostMapping
    public String sendEmail(@RequestParam(value = "email", required = false ) String email ){
        System.out.println(email);
        emailRecovery.sendTokenEmilRecovery(email);
        return "redirect:/auth/email/recovery";
    }
    
}
