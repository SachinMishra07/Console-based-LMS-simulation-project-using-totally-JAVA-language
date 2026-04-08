package lms;

import java.util.UUID;

public abstract class User {
    private String userId;
    private String username;
    private String email;
    private String password; // In a real app, this would be hashed and salted
    private boolean isActive;

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

    private static String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

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

    public void logout() {
        if (isActive) {
            System.out.println("✓ User '" + username + "' logged out successfully.");
        }
    }

    public abstract String getRole();

    // Getters and Setters (Encapsulation)
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

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

    public void setEmail(String email) throws ValidationException {
        if (!ValidationUtil.isValidEmail(email)) {
            throw new ValidationException("Invalid email format.");
        }
        this.email = email;
        System.out.println("✓ Email updated to: " + email);
    }

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

    public void deactivate() {
        this.isActive = false;
        System.out.println("✓ User account '" + username + "' deactivated.");
    }

    public void activate() {
        this.isActive = true;
        System.out.println("✓ User account '" + username + "' activated.");
    }
}
