package lms;

import java.util.regex.Pattern;

/**
 * Utility class for validating user input and system data.
 * Provides static methods for common validation tasks.
 */
public class ValidationUtil {
    
    // Email regex pattern following RFC 5322 simplified version
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    
    // Password must be at least 8 characters, contain uppercase, lowercase, and digit
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );
    
    /**
     * Validates email format.
     * @param email the email to validate
     * @return true if email is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Validates password strength.
     * Password must be at least 8 characters with uppercase, lowercase, digit, and special character.
     * @param password the password to validate
     * @return true if password meets strength requirements, false otherwise
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
    
    /**
     * Validates username format.
     * Username must be 3-20 characters, alphanumeric with underscores/hyphens allowed.
     * @param username the username to validate
     * @return true if username is valid, false otherwise
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        String trimmed = username.trim();
        return trimmed.length() >= 3 && trimmed.length() <= 20 && 
               trimmed.matches("^[a-zA-Z0-9_-]+$");
    }
    
    /**
     * Validates course title.
     * Title must be between 3 and 100 characters.
     * @param title the title to validate
     * @return true if title is valid, false otherwise
     */
    public static boolean isValidCourseTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return false;
        }
        String trimmed = title.trim();
        return trimmed.length() >= 3 && trimmed.length() <= 100;
    }
    
    /**
     * Validates course description.
     * Description must be between 10 and 1000 characters.
     * @param description the description to validate
     * @return true if description is valid, false otherwise
     */
    public static boolean isValidDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            return false;
        }
        String trimmed = description.trim();
        return trimmed.length() >= 10 && trimmed.length() <= 1000;
    }
    
    /**
     * Validates course syllabus.
     * Syllabus must be between 10 and 2000 characters.
     * @param syllabus the syllabus to validate
     * @return true if syllabus is valid, false otherwise
     */
    public static boolean isValidSyllabus(String syllabus) {
        if (syllabus == null || syllabus.trim().isEmpty()) {
            return false;
        }
        String trimmed = syllabus.trim();
        return trimmed.length() >= 10 && trimmed.length() <= 2000;
    }
    
    /**
     * Validates message content.
     * Message must be between 1 and 500 characters.
     * @param message the message to validate
     * @return true if message is valid, false otherwise
     */
    public static boolean isValidMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            return false;
        }
        String trimmed = message.trim();
        return trimmed.length() >= 1 && trimmed.length() <= 500;
    }
    
    /**
     * Validates material type.
     * @param type the material type to validate
     * @return true if type is valid, false otherwise
     */
    public static boolean isValidMaterialType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return false;
        }
        String lowerType = type.toLowerCase();
        return lowerType.equals("pdf") || lowerType.equals("video") || 
               lowerType.equals("quiz") || lowerType.equals("assignment") ||
               lowerType.equals("lecture");
    }
    
    /**
     * Validates user action for admin management.
     * @param action the action to validate
     * @return true if action is valid, false otherwise
     */
    public static boolean isValidUserAction(String action) {
        if (action == null || action.trim().isEmpty()) {
            return false;
        }
        String lowerAction = action.toLowerCase();
        return lowerAction.equals("create") || lowerAction.equals("update") || 
               lowerAction.equals("delete") || lowerAction.equals("suspend");
    }
    
    /**
     * Sanitizes string input to prevent injection attacks.
     * @param input the input to sanitize
     * @return sanitized string
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        return input.trim()
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}
