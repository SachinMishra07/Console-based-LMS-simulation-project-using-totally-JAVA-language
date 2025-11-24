package lms;

import java.util.UUID;

public abstract class User {
    private String userId;
    private String username;
    private String email;
    private String password;

    // Constructor to initialize common user details
    public User(String username, String email, String password) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Simulates user login functionality
    public void login() {
        System.out.println("User " + getUsername() + " is logging in.");
    }

    // Simulates user logout functionality
    public void logout() {
        System.out.println("User " + getUsername() + " is logging out.");
    }

    // Abstract method to force subclasses to define their role
    public abstract String getRole();

    // Getter for user ID
    public String getUserId() {
        return userId;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter for password with update notification
    public void setPassword(String password) {
        this.password = password;
        System.out.println("Password updated for " + getUsername());
    }
}