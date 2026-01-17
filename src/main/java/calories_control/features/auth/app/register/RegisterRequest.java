package calories_control.features.auth.app.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String confirmPassword;

    public RegisterRequest(String name, String email, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
