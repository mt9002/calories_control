package calories_control.auth.exception;

import calories_control.shared.ApiException;
import org.springframework.http.HttpStatus;

public class AuthenticationNotFound extends ApiException {
    public AuthenticationNotFound(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
