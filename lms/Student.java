package lms;

import java.util.ArrayList;
import java.util.List;

public class Student extends User implements StudentDashboard {

    private List<Course> enrolledCourses;
    private List<String> progress;

    // Constructor to initialize student specific lists
    public Student(String username, String email, String password) {
        super(username, email, password);
        this.enrolledCourses = new ArrayList<>();
        this.progress = new ArrayList<>();
    }

    // Returns the role identifier for students
    @Override
    public String getRole() {
        return "Student";
    }

    // Enrolls student in a course if it is approved
    @Override
    public void enrollInCourse(Course course) {
        if (course.isApproved()) {
            this.enrolledCourses.add(course);
            course.addStudent(this);
            System.out.println("Student " + getUsername() + " enrolled in " + course.getTitle());
            System.out.println("Confirmation: You are now enrolled.");
        } else {
            System.out.println("Cannot enroll in " + course.getTitle() + ". Course is not yet approved.");
        }
    }

    // Displays materials for a course if the student is enrolled
    @Override
    public void accessMaterials(Course course) {
        if (enrolledCourses.contains(course)) {
            System.out.println("Accessing materials for " + course.getTitle() + ":");
            for (CourseMaterial material : course.getMaterials()) {
                System.out.println(" - " + material.getTitle() + " (" + material.getType() + ")");
            }
        } else {
            System.out.println("You are not enrolled in this course.");
        }
    }

    // Displays the student's progress log
    @Override
    public void trackProgress() {
        System.out.println("Progress for " + getUsername() + ":");
        if (progress.isEmpty()) {
            System.out.println("No achievements yet.");
        }
        for (String achievement : progress) {
            System.out.println(" - " + achievement);
        }
    }

    // Updates student profile information
    @Override
    public void updateProfile(String newEmail, String newPassword) {
        if (newEmail != null && !newEmail.isEmpty()) {
            setEmail(newEmail);
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            setPassword(newPassword);
        }
        System.out.println("Profile updated successfully for " + getUsername());
    }

    // Adds a new achievement to the progress list
    public void addAchievement(String achievement) {
        this.progress.add(achievement);
        System.out.println("Achievement unlocked: " + achievement);
    }
}