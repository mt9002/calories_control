package calories_control.user.exception;

import calories_control.shared.ApiException;
import org.springframework.http.HttpStatus;

public class AvatarNotFoundException extends ApiException {

    public AvatarNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
