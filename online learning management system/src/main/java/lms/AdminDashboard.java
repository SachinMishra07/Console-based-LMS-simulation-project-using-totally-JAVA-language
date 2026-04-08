package lms;
public interface AdminDashboard {
    
    void manageUser(User user, String action) throws UserManagementException;
    
    void approveCourse(Course course) throws CourseException;
    
    void rejectCourse(Course course) throws CourseException;
    
    void configureSystemSettings(String setting, String value) throws UserManagementException;
    
    void monitorPerformanceAnalytics();
}
