package calories_control.imc.exception;

import calories_control.shared.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidImcDataException extends ApiException {
    public InvalidImcDataException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
