package calories_control.auth.infra.security.util;

public class Result<T> {

    private boolean success;
    private T data;
    private String message;
    private int code;

    // Constructor privado para controlar creación
    private Result(boolean success, T data, String message, int code) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> Result<T> failure(String message, int code) {
        return new Result<>(false, null, message, code);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, null, 200);
    }
    // Getters (para serializar a JSON)
    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
