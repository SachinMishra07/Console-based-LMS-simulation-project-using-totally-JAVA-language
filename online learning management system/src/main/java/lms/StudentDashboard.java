package lms;

public interface StudentDashboard {
    
    void enrollInCourse(Course course) throws EnrollmentException;
    void accessMaterials(Course course) throws CourseException;
    void trackProgress();
    void updateProfile(String newEmail, String newPassword) throws ValidationException;
    
}
