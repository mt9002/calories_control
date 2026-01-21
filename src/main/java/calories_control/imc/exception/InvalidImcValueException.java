package calories_control.imc.exception;

import calories_control.shared.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidImcValueException extends ApiException {
    public InvalidImcValueException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }
}
