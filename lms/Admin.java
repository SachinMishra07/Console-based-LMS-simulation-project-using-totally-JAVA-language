package lms;

import java.util.List;

public class Admin extends User implements AdminDashboard {

    // Constructor for admin user
    public Admin(String username, String email, String password) {
        super(username, email, password);
    }

    // Returns the role identifier for admins
    @Override
    public String getRole() {
        return "Admin";
    }

    // Manages user creation, updates, and deletion
    @Override
    public void manageUser(User user, String action) {
        switch (action.toLowerCase()) {
            case "create":
                System.out.println("Admin " + getUsername() + " creating new user: " + (user != null ? user.getUsername() : "<unknown>"));
                break;
            case "update":
                System.out.println("Admin " + getUsername() + " updating user: " + (user != null ? user.getUsername() : "<unknown>"));
                break;
            case "delete":
                System.out.println("Admin " + getUsername() + " deleting user: " + (user != null ? user.getUsername() : "<unknown>"));
                break;
            default:
                System.out.println("Unknown user management action.");
        }
        System.out.println("Confirmation: User action '" + action + "' completed.");
    }

    // Approves a course to make it available for enrollment
    @Override
    public void approveCourse(Course course) {
        course.setApproved(true);
        System.out.println("Course '" + course.getTitle() + "' approved by " + getUsername() + " and is now live.");
    }

    // Rejects a course request
    @Override
    public void rejectCourse(Course course) {
        course.setApproved(false);
        System.out.println("Course '" + course.getTitle() + "' rejected by " + getUsername() + ".");
    }

    // Updates system-wide settings
    @Override
    public void configureSystemSettings(String setting, String value) {
        System.out.println("Admin " + getUsername() + " set system setting: " + setting + " = " + value);
        System.out.println("Confirmation: Setting updated.");
    }

    // Displays mock performance analytics
    @Override
    public void monitorPerformanceAnalytics() {
        System.out.println("Visualizing system usage and performance metrics:");
        System.out.println("- Total Users: (querying...)");
        System.out.println("- Active Courses: (querying...)");
        System.out.println("- Platform Health: OK");
    }
}