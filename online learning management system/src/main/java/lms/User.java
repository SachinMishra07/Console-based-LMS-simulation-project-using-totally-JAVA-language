package lms;

import java.util.UUID;

/**
 * Base class for all users in the Learning Management System.
 * It holds common properties like user ID, name, and email.
 * Student, Instructor, and Admin will inherit from this class.
 * 
 * Features:
 * - User authentication with validation
 * - Input validation for email and password
 * - Secure password handling
 * - Error handling for invalid operations
 */
public abstract class User {
    private String userId;
    private String username;
    private String email;
    private String password; // In a real app, this would be hashed and salted
    private boolean isActive;

    /**
     * Constructor for User with validation.
     * @param username the username
     * @param email the email address
     * @param password the password
     * @throws ValidationException if any parameter is invalid
     */
    public User(String username, String email, String password) throws ValidationException {
        if (!ValidationUtil.isValidUsername(username)) {
            throw new ValidationException("Invalid username. Must be 3-20 alphanumeric characters.");
        }
        if (!ValidationUtil.isValidEmail(email)) {
            throw new ValidationException("Invalid email format.");
        }
        if (!ValidationUtil.isValidPassword(password)) {
            throw new ValidationException("Password must be at least 8 characters with uppercase, lowercase, digit, and special character.");
        }
        
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = hashPassword(password);
        this.isActive = true;
    }

    /**
     * Simple password hashing (in production, use bcrypt or similar).
     * @param password the password to hash
     * @return the hashed password
     */
    private static String hashPassword(String password) {
        // In production, use: BCryptPasswordEncoder or similar
        return Integer.toHexString(password.hashCode());
    }

    /**
     * Authenticates user login with password verification.
     * @param password the password to verify
     * @return true if authentication successful
     */
    public boolean login(String password) {
        if (!isActive) {
            System.out.println("ERROR: User account is inactive.");
            return false;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("ERROR: Password cannot be empty.");
            return false;
        }
        if (hashPassword(password).equals(this.password)) {
            System.out.println("✓ User '" + username + "' logged in successfully.");
            return true;
        } else {
            System.out.println("ERROR: Invalid password for user '" + username + "'.");
            return false;
        }
    }

    /**
     * Logs out the user.
     */
    public void logout() {
        if (isActive) {
            System.out.println("✓ User '" + username + "' logged out successfully.");
        }
    }

    /**
     * Abstract method to be implemented by subclasses.
     * @return the role of the user
     */
    public abstract String getRole();

    // Getters and Setters (Encapsulation)
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Sets username with validation.
     * @param username the new username
     * @throws ValidationException if username is invalid
     */
    public void setUsername(String username) throws ValidationException {
        if (!ValidationUtil.isValidUsername(username)) {
            throw new ValidationException("Invalid username. Must be 3-20 alphanumeric characters.");
        }
        this.username = username;
        System.out.println("✓ Username updated to: " + username);
    }

    public String getEmail() {
        return email;
    }

    /**
     * Sets email with validation.
     * @param email the new email
     * @throws ValidationException if email is invalid
     */
    public void setEmail(String email) throws ValidationException {
        if (!ValidationUtil.isValidEmail(email)) {
            throw new ValidationException("Invalid email format.");
        }
        this.email = email;
        System.out.println("✓ Email updated to: " + email);
    }

    /**
     * Updates password with validation.
     * @param newPassword the new password
     * @throws ValidationException if password is invalid
     */
    public void setPassword(String newPassword) throws ValidationException {
        if (!ValidationUtil.isValidPassword(newPassword)) {
            throw new ValidationException("Password must be at least 8 characters with uppercase, lowercase, digit, and special character.");
        }
        this.password = hashPassword(newPassword);
        System.out.println("✓ Password updated for " + username);
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Deactivates the user account.
     */
    public void deactivate() {
        this.isActive = false;
        System.out.println("✓ User account '" + username + "' deactivated.");
    }

    /**
     * Reactivates the user account.
     */
    public void activate() {
        this.isActive = true;
        System.out.println("✓ User account '" + username + "' activated.");
    }
}
