package calories_control.features.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> { // T es un tipo generico, le agrego el tipo requiera.

    private String message;
    private State state;
    private T data;

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(message, State.SUCCESS, data);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(message, State.SUCCESS, null);
    }

    public static <T> Result<T> failure(String message, State state) {
        return new Result<>(message, state, null);
    }

    public static <T> Result<T> failure(String message, T errorData, State state) {
        return new Result<>(message, state, errorData);
    }

}