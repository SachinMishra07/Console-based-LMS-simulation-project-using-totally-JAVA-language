package lms;

public interface StudentDashboard {
    // Defines method to enroll in a course
    void enrollInCourse(Course course);
    // Defines method to access course materials
    void accessMaterials(Course course);
    // Defines method to track student progress
    void trackProgress();
    // Defines method to update profile details
    void updateProfile(String newEmail, String newPassword);
}