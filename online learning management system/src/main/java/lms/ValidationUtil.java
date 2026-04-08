package lms;

import java.util.regex.Pattern;

public class ValidationUtil {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    public static boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        String trimmed = username.trim();
        return trimmed.length() >= 3 && trimmed.length() <= 20 && 
               trimmed.matches("^[a-zA-Z0-9_-]+$");
    }

    public static boolean isValidCourseTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return false;
        }
        String trimmed = title.trim();
        return trimmed.length() >= 3 && trimmed.length() <= 100;
    }

    public static boolean isValidDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            return false;
        }
        String trimmed = description.trim();
        return trimmed.length() >= 10 && trimmed.length() <= 1000;
    }

    public static boolean isValidSyllabus(String syllabus) {
        if (syllabus == null || syllabus.trim().isEmpty()) {
            return false;
        }
        String trimmed = syllabus.trim();
        return trimmed.length() >= 10 && trimmed.length() <= 2000;
    }

    public static boolean isValidMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            return false;
        }
        String trimmed = message.trim();
        return trimmed.length() >= 1 && trimmed.length() <= 500;
    }

    public static boolean isValidMaterialType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return false;
        }
        String lowerType = type.toLowerCase();
        return lowerType.equals("pdf") || lowerType.equals("video") || 
               lowerType.equals("quiz") || lowerType.equals("assignment") ||
               lowerType.equals("lecture");
    }

    public static boolean isValidUserAction(String action) {
        if (action == null || action.trim().isEmpty()) {
            return false;
        }
        String lowerAction = action.toLowerCase();
        return lowerAction.equals("create") || lowerAction.equals("update") || 
               lowerAction.equals("delete") || lowerAction.equals("suspend");
    }
    
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
