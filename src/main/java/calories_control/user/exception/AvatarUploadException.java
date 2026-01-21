package calories_control.user.exception;

import calories_control.shared.ApiException;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class AvatarUploadException extends ApiException {

    public AvatarUploadException(String message, IOException e) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
