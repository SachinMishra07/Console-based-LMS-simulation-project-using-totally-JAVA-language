package lms;

/**
 * Custom exception for course-related errors.
 * Used when course operations fail.
 */
public class CourseException extends Exception {
    public CourseException(String message) {
        super(message);
    }

    public CourseException(String message, Throwable cause) {
        super(message, cause);
    }
}
