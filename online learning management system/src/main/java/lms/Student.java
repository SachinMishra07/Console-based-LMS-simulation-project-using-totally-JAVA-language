package lms;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Student user.
 * Inherits from User and implements the StudentDashboard functionalities.
 * 
 * Features:
 * - Course enrollment with validation
 * - Access course materials
 * - Track progress and achievements
 * - Update profile with validation
 * - Error handling for invalid operations
 */
public class Student extends User implements StudentDashboard {

    private List<Course> enrolledCourses;
    private List<String> achievements; // Achievement tracking
    private double progressPercentage;

    /**
     * Constructor for Student with validation.
     * @param username the username
     * @param email the email address
     * @param password the password
     * @throws ValidationException if any parameter is invalid
     */
    public Student(String username, String email, String password) throws ValidationException {
        super(username, email, password);
        this.enrolledCourses = new ArrayList<>();
        this.achievements = new ArrayList<>();
        this.progressPercentage = 0.0;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    /**
     * Enrolls the student in a course with validation.
     * @param course the course to enroll in
     * @throws EnrollmentException if enrollment fails
     */
    @Override
    public void enrollInCourse(Course course) throws EnrollmentException {
        if (course == null) {
            throw new EnrollmentException("Invalid course. Cannot be null.");
        }
        if (!course.isApproved()) {
            throw new EnrollmentException("Cannot enroll in '" + course.getTitle() + "'. Course is not yet approved.");
        }
        if (enrolledCourses.contains(course)) {
            throw new EnrollmentException("Already enrolled in course '" + course.getTitle() + "'.");
        }

        try {
            course.addStudent(this);
            enrolledCourses.add(course);
            System.out.println("✓ Student '" + getUsername() + "' successfully enrolled in '" + course.getTitle() + "'.");
        } catch (EnrollmentException e) {
            System.out.println("ERROR: Enrollment failed - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Drops a course.
     * @param course the course to drop
     * @return true if successful, false otherwise
     */
    public boolean dropCourse(Course course) {
        if (enrolledCourses.remove(course)) {
            course.removeStudent(this);
            System.out.println("✓ Dropped course '" + course.getTitle() + "'.");
            return true;
        }
        System.out.println("ERROR: Course not found in enrollment list.");
        return false;
    }

    /**
     * Allows student to access course materials if enrolled.
     * @param course the course to access
     * @throws CourseException if course is not accessible
     */
    @Override
    public void accessMaterials(Course course) throws CourseException {
        if (course == null) {
            throw new CourseException("Invalid course. Cannot be null.");
        }
        if (!enrolledCourses.contains(course)) {
            throw new CourseException("You are not enrolled in '" + course.getTitle() + "'.");
        }

        List<CourseMaterial> materials = course.getMaterials();
        System.out.println("\n--- Course Materials for '" + course.getTitle() + "' ---");
        if (materials.isEmpty()) {
            System.out.println("No materials available yet.");
        } else {
            for (int i = 0; i < materials.size(); i++) {
                CourseMaterial material = materials.get(i);
                System.out.println((i + 1) + ". " + material.getTitle() + " [" + material.getType() + "]");
            }
        }
    }

    /**
     * Tracks student progress.
     */
    @Override
    public void trackProgress() {
        System.out.println("\n--- Progress Report for " + getUsername() + " ---");
        System.out.println("Enrolled Courses: " + enrolledCourses.size());
        System.out.println("Overall Progress: " + String.format("%.2f", progressPercentage) + "%");
        
        if (achievements.isEmpty()) {
            System.out.println("No achievements yet. Keep learning!");
        } else {
            System.out.println("Achievements:");
            for (String achievement : achievements) {
                System.out.println("  ✓ " + achievement);
            }
        }
    }

    /**
     * Updates student profile with validation.
     * @param newEmail the new email
     * @param newPassword the new password
     * @throws ValidationException if update fails
     */
    @Override
    public void updateProfile(String newEmail, String newPassword) throws ValidationException {
        boolean updated = false;

        if (newEmail != null && !newEmail.trim().isEmpty()) {
            if (!ValidationUtil.isValidEmail(newEmail)) {
                throw new ValidationException("Invalid email format.");
            }
            setEmail(newEmail);
            updated = true;
        }

        if (newPassword != null && !newPassword.isEmpty()) {
            if (!ValidationUtil.isValidPassword(newPassword)) {
                throw new ValidationException("Password must be at least 8 characters with uppercase, lowercase, digit, and special character.");
            }
            setPassword(newPassword);
            updated = true;
        }

        if (updated) {
            System.out.println("✓ Profile updated successfully.");
        } else {
            System.out.println("No changes made to profile.");
        }
    }

    /**
     * Adds an achievement to the student's record.
     * @param achievement the achievement description
     */
    public void addAchievement(String achievement) {
        if (achievement != null && !achievement.trim().isEmpty()) {
            achievements.add(achievement);
            System.out.println("✓ Achievement unlocked: " + achievement);
        } else {
            System.out.println("ERROR: Invalid achievement.");
        }
    }

    /**
     * Updates progress percentage.
     * @param percentage the progress percentage (0-100)
     * @throws ValidationException if percentage is invalid
     */
    public void updateProgress(double percentage) throws ValidationException {
        if (percentage < 0 || percentage > 100) {
            throw new ValidationException("Progress must be between 0 and 100.");
        }
        this.progressPercentage = percentage;
        System.out.println("✓ Progress updated to: " + String.format("%.2f", percentage) + "%");
    }

    // Getters
    public List<Course> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    public int getEnrollmentCount() {
        return enrolledCourses.size();
    }

    public List<String> getAchievements() {
        return new ArrayList<>(achievements);
    }

    public double getProgressPercentage() {
        return progressPercentage;
    }

    public boolean isEnrolledInCourse(Course course) {
        return enrolledCourses.contains(course);
    }
}
