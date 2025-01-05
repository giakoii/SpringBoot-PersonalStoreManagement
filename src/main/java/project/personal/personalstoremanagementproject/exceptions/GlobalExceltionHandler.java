package project.personal.personalstoremanagementproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.personal.personalstoremanagementproject.controllers.ConcreteApiResponse;
import project.personal.personalstoremanagementproject.utils.MessageId;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceltionHandler {

    /**
     * Handles MethodArgumentNotValidExceptions
     * @param ex the exception to handle
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ConcreteApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<DetailError> detailErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(this::mapFieldErrorToDetailError)
                .collect(Collectors.toList());

        ConcreteApiResponse<?> response = new ConcreteApiResponse<>();
        response.setSuccess(false);
        response.setMessage(MessageId.E0000, "Validation failed");
        response.setDetailErrorList(detailErrors);
        response.setResponse(null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IllegalArgumentExceptions
     * @param ex the exception to handle
     * @return the response entity
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ConcreteApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        String message = ex.getMessage();

        ConcreteApiResponse<?> response = new ConcreteApiResponse<>();
        response.setSuccess(false);
        response.setMessage(MessageId.E0000, message);
        response.setDetailErrorList(null);
        response.setResponse(null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maps a FieldError to a DetailError
     * @param error the FieldError to map
     * @return the mapped DetailError
     */
    private DetailError mapFieldErrorToDetailError(FieldError error) {
        String errorCode = getErrorCode(error);
        String errorMessage = error.getField() + " " + getErrorMessage(error);

        return new DetailError(error.getField(), errorCode, errorMessage);
    }

    /**
     * Gets the error code for a FieldError
     * @param error the FieldError to get the code for
     * @return the error code
     */
    private String getErrorCode(FieldError error) {
        return switch (error.getCode()) {
            case "NotBlank" -> "REQUIRED_FIELD";
            case "Size" -> "INVALID_SIZE";
            case "Pattern" -> "INVALID_FORMAT";
            case "Email" -> "INVALID_EMAIL";
            case "Min" -> "MINIMUM_VALUE";
            case "Max" -> "MAXIMUM_VALUE";
            case "NotNull" -> "NOT_NULL";
            default -> "UNKNOWN_ERROR";
        };
    }

    /**
     * Gets the error message for a FieldError
     * @param error the FieldError to get the message for
     * @return the error message
     */
    private String getErrorMessage(FieldError error) {
        return switch (error.getCode()) {
            case "NotBlank" -> "is required";
            case "Size" -> "must be between " + error.getArguments()[1] + " and " + error.getArguments()[2] + " characters";
            case "Pattern" -> "must match the pattern " + error.getArguments()[1];
            case "Email" -> "must be a valid email address";
            case "Min" -> "must be greater than or equal to " + error.getArguments()[1];
            case "Max" -> "must be less than or equal to " + error.getArguments()[1];
            case "NotNull" -> "must not be null";
            default -> "an unknown error occurred";
        };
    }
}