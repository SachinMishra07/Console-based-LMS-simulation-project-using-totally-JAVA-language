package lms;

/**
 * Custom exception for enrollment-related errors.
 * Used when student enrollment operations fail.
 */
public class EnrollmentException extends Exception {
    public EnrollmentException(String message) {
        super(message);
    }

    public EnrollmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
