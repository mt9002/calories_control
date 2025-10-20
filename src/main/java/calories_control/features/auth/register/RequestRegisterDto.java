package calories_control.features.auth.register;

import lombok.Data;

@Data
public class RequestRegisterDto {
    private String email;
    private String password;
    private String name;
    private String confirmPassword;
}
