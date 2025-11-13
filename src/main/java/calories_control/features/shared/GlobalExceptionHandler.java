package calories_control.features.shared;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    public String handleDatabaseError(NoSuchElementException ex, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        logger.error("Database error: No such element", ex);
        String referer = request.getHeader("Referer");
        logger.info("Referer header: {}", referer);
        redirectAttributes.addFlashAttribute("error", "Error en la base de datos. Intenta más tarde.");

        // redirige a la página desde la que vino el usuario
        return "redirect:" + referer;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityError(DataIntegrityViolationException ex, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        logger.error("Data integrity violation", ex);
        String referer = request.getHeader("Referer");
        logger.info("Referer header: {}", referer);
        redirectAttributes.addFlashAttribute("error",
                "Error: datos inválidos. Asegúrate de completar todos los campos obligatorios.");
        return "redirect:" + referer;
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralError(Exception ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        logger.error("Unexpected error occurred", ex);
        String referer = request.getHeader("Referer");
        logger.info("Referer header: {}", referer);
        redirectAttributes.addFlashAttribute("error", "Ha ocurrido un error inesperado.");
        return "redirect:" + referer;
    }

}
