package lms;

/**
 * Custom exception for user management-related errors.
 * Used when user operations fail.
 */
public class UserManagementException extends Exception {
    public UserManagementException(String message) {
        super(message);
    }

    public UserManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
