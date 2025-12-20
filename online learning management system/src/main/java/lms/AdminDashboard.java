package lms;

/**
 * Interface defining the functionalities available to an Admin.
 * Represents the Admin Dashboard and administrative operations.
 * 
 * Operations:
 * - Manage user accounts (create, update, delete, suspend)
 * - Approve or reject courses
 * - Configure system settings
 * - Monitor system performance and analytics
 */
public interface AdminDashboard {
    
    /**
     * Manages user accounts in the system.
     * @param user the user to manage
     * @param action the action to perform (create, update, delete, suspend)
     * @throws UserManagementException if operation fails
     */
    void manageUser(User user, String action) throws UserManagementException;
    
    /**
     * Approves a course for enrollment.
     * @param course the course to approve
     * @throws CourseException if operation fails
     */
    void approveCourse(Course course) throws CourseException;
    
    /**
     * Rejects a course from being offered.
     * @param course the course to reject
     * @throws CourseException if operation fails
     */
    void rejectCourse(Course course) throws CourseException;
    
    /**
     * Configures system-wide settings.
     * @param setting the setting name
     * @param value the setting value
     * @throws UserManagementException if configuration fails
     */
    void configureSystemSettings(String setting, String value) throws UserManagementException;
    
    /**
     * Monitors and displays system performance analytics.
     */
    void monitorPerformanceAnalytics();
}
