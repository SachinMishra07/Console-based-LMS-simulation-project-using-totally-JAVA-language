package lms;

import java.io.*;

/**
 * UserServlet - Handles user-related HTTP requests.
 * 
 * This servlet demonstrates how LMS user operations would be handled
 * in a web application context. In a real application, this would
 * integrate with servlets in a web server.
 */
public class UserServlet {
    
    /**
     * Simulates handling user registration request.
     * @param username the username
     * @param email the email
     * @param password the password
     * @param role the user role (Student, Instructor, Admin)
     * @return response message
     */
    public static String handleUserRegistration(String username, String email, String password, String role) {
        try {
            // Validate input
            if (!ValidationUtil.isValidUsername(username)) {
                return "ERROR: Invalid username format.";
            }
            if (!ValidationUtil.isValidEmail(email)) {
                return "ERROR: Invalid email format.";
            }
            if (!ValidationUtil.isValidPassword(password)) {
                return "ERROR: Password does not meet security requirements.";
            }

            // Create appropriate user based on role
            User user;
            try {
                if ("Student".equalsIgnoreCase(role)) {
                    user = new Student(username, email, password);
                } else if ("Instructor".equalsIgnoreCase(role)) {
                    user = new Instructor(username, email, password);
                } else if ("Admin".equalsIgnoreCase(role)) {
                    user = new Admin(username, email, password);
                } else {
                    return "ERROR: Invalid role. Must be Student, Instructor, or Admin.";
                }

                return "SUCCESS: User '" + username + "' registered as " + user.getRole();
            } catch (ValidationException e) {
                return "ERROR: Registration failed - " + e.getMessage();
            }
        } catch (Exception e) {
            return "ERROR: Unexpected error during registration - " + e.getMessage();
        }
    }

    /**
     * Simulates handling user login request.
     * @param user the user attempting login
     * @param password the password to verify
     * @return response message
     */
    public static String handleUserLogin(User user, String password) {
        try {
            if (user == null) {
                return "ERROR: User not found.";
            }
            if (password == null || password.isEmpty()) {
                return "ERROR: Password cannot be empty.";
            }

            if (user.login(password)) {
                return "SUCCESS: Login successful for " + user.getUsername();
            } else {
                return "ERROR: Invalid credentials.";
            }
        } catch (Exception e) {
            return "ERROR: Login failed - " + e.getMessage();
        }
    }

    /**
     * Simulates handling user profile update request.
     * @param user the user to update
     * @param newEmail the new email
     * @param newPassword the new password
     * @return response message
     */
    public static String handleProfileUpdate(User user, String newEmail, String newPassword) {
        try {
            if (user == null) {
                return "ERROR: User not found.";
            }

            if (newEmail != null && !newEmail.trim().isEmpty()) {
                user.setEmail(newEmail);
            }
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }

            return "SUCCESS: Profile updated for " + user.getUsername();
        } catch (ValidationException e) {
            return "ERROR: Profile update failed - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }

    /**
     * Simulates handling user logout.
     * @param user the user logging out
     * @return response message
     */
    public static String handleUserLogout(User user) {
        if (user == null) {
            return "ERROR: Invalid user.";
        }
        user.logout();
        return "SUCCESS: User logged out.";
    }
}
