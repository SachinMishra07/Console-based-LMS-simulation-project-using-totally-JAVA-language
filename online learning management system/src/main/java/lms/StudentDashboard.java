package lms;

/**
 * Interface defining the functionalities available to a Student.
 * Represents the Student Dashboard and student-specific operations.
 * 
 * Operations:
 * - Enroll in approved courses
 * - Access course materials
 * - Track progress and achievements
 * - Update personal profile
 */
public interface StudentDashboard {
    
    /**
     * Enrolls the student in a course.
     * @param course the course to enroll in
     * @throws EnrollmentException if enrollment fails
     */
    void enrollInCourse(Course course) throws EnrollmentException;
    
    /**
     * Allows the student to access course materials.
     * @param course the course to access materials from
     * @throws CourseException if access is denied
     */
    void accessMaterials(Course course) throws CourseException;
    
    /**
     * Tracks the student's progress and achievements.
     */
    void trackProgress();
    
    /**
     * Updates the student's profile information.
     * @param newEmail the new email address
     * @param newPassword the new password
     * @throws ValidationException if update fails
     */
    void updateProfile(String newEmail, String newPassword) throws ValidationException;
}
