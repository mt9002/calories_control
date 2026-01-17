package calories_control.features.shared;

public class ErrorResponse {
    private String error;
    private String message;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    // getters y setters
    public String getError() { return error; }
    public String getMessage() { return message; }
}
