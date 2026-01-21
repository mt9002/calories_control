package calories_control.user.exception;

import calories_control.shared.ApiException;
import org.springframework.http.HttpStatus;

public class UsuarioDuplicadoException extends ApiException {

    public UsuarioDuplicadoException(String mensaje) {
        super(mensaje, HttpStatus.CONFLICT);
    }
}