package calories_control.features.auth.app.register;


import calories_control.features.auth.app.login.LoginRequest;
import calories_control.features.auth.app.login.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registerService.register(registerRequest));
    }
}