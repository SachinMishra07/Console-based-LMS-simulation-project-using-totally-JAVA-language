package lms;

/**
 * Custom exception for validation-related errors.
 * Used when user input or data validation fails.
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
