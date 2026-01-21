package calories_control.auth.exception;

import calories_control.shared.ApiException;
import org.springframework.http.HttpStatus;

public class LoginNotFoundException extends ApiException {

    public LoginNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
