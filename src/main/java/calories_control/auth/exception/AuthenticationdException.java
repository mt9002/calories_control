package calories_control.auth.exception;

import calories_control.shared.ApiException;
import org.springframework.http.HttpStatus;

public class AuthenticationdException extends ApiException {
    public AuthenticationdException(String message){
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
