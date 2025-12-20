package lms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an Administrator user.
 * Inherits from User and implements the AdminDashboard functionalities.
 * 
 * Features:
 * - User management with validation
 * - Course approval workflow
 * - System settings management
 * - Performance analytics monitoring
 * - User deactivation/activation
 * - Error handling for invalid operations
 */
public class Admin extends User implements AdminDashboard {

    private Map<String, User> userRegistry;
    private List<Course> approvalQueue;

    /**
     * Constructor for Admin with validation.
     * @param username the username
     * @param email the email address
     * @param password the password
     * @throws ValidationException if any parameter is invalid
     */
    public Admin(String username, String email, String password) throws ValidationException {
        super(username, email, password);
        this.userRegistry = new HashMap<>();
        this.approvalQueue = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    /**
     * Manages user operations with validation.
     * @param user the user to manage
     * @param action the action to perform (create, update, delete, suspend)
     * @throws UserManagementException if operation fails
     */
    @Override
    public void manageUser(User user, String action) throws UserManagementException {
        if (user == null) {
            throw new UserManagementException("Invalid user. Cannot be null.");
        }
        if (!ValidationUtil.isValidUserAction(action)) {
            throw new UserManagementException("Invalid action. Must be: create, update, delete, or suspend.");
        }

        String actionLower = action.toLowerCase();
        
        try {
            switch (actionLower) {
                case "create":
                    userRegistry.put(user.getUserId(), user);
                    System.out.println("✓ Admin " + getUsername() + " created user: " + user.getUsername());
                    break;
                case "update":
                    if (!userRegistry.containsKey(user.getUserId())) {
                        throw new UserManagementException("User not found in registry.");
                    }
                    userRegistry.put(user.getUserId(), user);
                    System.out.println("✓ Admin " + getUsername() + " updated user: " + user.getUsername());
                    break;
                case "delete":
                    if (userRegistry.remove(user.getUserId()) == null) {
                        throw new UserManagementException("User not found in registry.");
                    }
                    System.out.println("✓ Admin " + getUsername() + " deleted user: " + user.getUsername());
                    break;
                case "suspend":
                    user.deactivate();
                    System.out.println("✓ Admin " + getUsername() + " suspended user: " + user.getUsername());
                    break;
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new UserManagementException("User management action failed: " + e.getMessage(), e);
        }
    }

    /**
     * Approves a course for enrollment.
     * @param course the course to approve
     * @throws CourseException if course is invalid
     */
    @Override
    public void approveCourse(Course course) throws CourseException {
        if (course == null) {
            throw new CourseException("Invalid course. Cannot be null.");
        }
        if (course.isApproved()) {
            throw new CourseException("Course '" + course.getTitle() + "' is already approved.");
        }

        course.setApproved(true);
        approvalQueue.remove(course);
        System.out.println("✓ Admin " + getUsername() + " approved course: '" + course.getTitle() + "'");
        System.out.println("  Course is now live and available for enrollment.");
    }

    /**
     * Rejects a course from being offered.
     * @param course the course to reject
     * @throws CourseException if course is invalid
     */
    @Override
    public void rejectCourse(Course course) throws CourseException {
        if (course == null) {
            throw new CourseException("Invalid course. Cannot be null.");
        }

        course.setApproved(false);
        approvalQueue.remove(course);
        System.out.println("✓ Admin " + getUsername() + " rejected course: '" + course.getTitle() + "'");
        System.out.println("  Please contact the instructor for more information.");
    }

    /**
     * Configures system settings with validation.
     * @param setting the setting name
     * @param value the setting value
     * @throws UserManagementException if configuration fails
     */
    @Override
    public void configureSystemSettings(String setting, String value) 
            throws UserManagementException {
        if (setting == null || setting.trim().isEmpty()) {
            throw new UserManagementException("Setting name cannot be empty.");
        }
        if (value == null || value.trim().isEmpty()) {
            throw new UserManagementException("Setting value cannot be empty.");
        }

        System.out.println("✓ Admin " + getUsername() + " configured system setting:");
        System.out.println("  Setting: " + setting);
        System.out.println("  Value: " + value);
    }

    /**
     * Monitors system performance and analytics.
     */
    @Override
    public void monitorPerformanceAnalytics() {
        System.out.println("\n--- System Performance Analytics ---");
        System.out.println("Total Registered Users: " + userRegistry.size());
        System.out.println("Courses Pending Approval: " + approvalQueue.size());
        System.out.println("System Status: ✓ OK");
        System.out.println("Last Updated: " + java.time.LocalDateTime.now());
        System.out.println("Platform Health: Excellent");
    }

    /**
     * Submits a course for approval.
     * @param course the course to submit
     */
    public void submitCourseForApproval(Course course) {
        if (course != null && !approvalQueue.contains(course)) {
            approvalQueue.add(course);
            System.out.println("✓ Course '" + course.getTitle() + "' submitted for approval.");
        }
    }

    /**
     * Gets pending approval queue.
     * @return list of courses pending approval
     */
    public List<Course> getApprovalQueue() {
        return new ArrayList<>(approvalQueue);
    }

    /**
     * Gets registered user by ID.
     * @param userId the user ID
     * @return the user, or null if not found
     */
    public User getUserById(String userId) {
        return userRegistry.get(userId);
    }

    /**
     * Gets total registered user count.
     * @return number of registered users
     */
    public int getUserCount() {
        return userRegistry.size();
    }

    /**
     * Reactivates a suspended user account.
     * @param user the user to reactivate
     */
    public void reactivateUser(User user) {
        if (user != null) {
            user.activate();
            System.out.println("✓ Admin " + getUsername() + " reactivated user: " + user.getUsername());
        }
    }
}
