package calories_control.features.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AvatarUploadException extends RuntimeException {

    public AvatarUploadException(String message) {
        super(message);
    }

    public AvatarUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
