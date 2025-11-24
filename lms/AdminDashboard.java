package lms;

public interface AdminDashboard {
    // Defines method to manage user accounts
    void manageUser(User user, String action);
    // Defines method to approve a pending course
    void approveCourse(Course course);
    // Defines method to reject a pending course
    void rejectCourse(Course course);
    // Defines method to configure system settings
    void configureSystemSettings(String setting, String value);
    // Defines method to monitor system performance
    void monitorPerformanceAnalytics();
}