package lms;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User implements InstructorDashboard {

    private List<Course> createdCourses;

    // Constructor to initialize instructor
    public Instructor(String username, String email, String password) {
        super(username, email, password);
        this.createdCourses = new ArrayList<>();
    }

    // Returns the role identifier for instructors
    @Override
    public String getRole() {
        return "Instructor";
    }

    // Creates a new course instance and adds it to the list
    @Override
    public Course createCourse(String title, String description, String syllabus) {
        Course newCourse = new Course(title, description, syllabus, this);
        this.createdCourses.add(newCourse);
        System.out.println("Course '" + title + "' created by " + getUsername() + ". Pending admin approval.");
        System.out.println("Confirmation: Course created.");
        return newCourse;
    }

    // Displays progress of all students in a specific course
    @Override
    public void trackStudentProgress(Course course) {
        if (createdCourses.contains(course)) {
            System.out.println("Generating progress report for " + course.getTitle() + ":");
            List<Student> students = course.getEnrolledStudents();
            if (students.isEmpty()) {
                System.out.println("No students enrolled yet.");
                return;
            }
            for (Student student : students) {
                System.out.println(" - " + student.getUsername() + ": (progress data would be here)");
            }
        } else {
            System.out.println("You do not own this course.");
        }
    }

    // Simulates sending a message to all enrolled students
    @Override
    public void communicateWithStudents(Course course, String message) {
        System.out.println("Sending message from " + getUsername() + " to all students in " + course.getTitle() + ":");
        System.out.println("Message: '" + message + "'");
        System.out.println("Confirmation: Message sent successfully.");
    }

    // Displays the count of students enrolled in a course
    @Override
    public void viewEnrollmentStats(Course course) {
        if (createdCourses.contains(course)) {
            System.out.println("Enrollment stats for " + course.getTitle() + ":");
            System.out.println("Total students: " + course.getEnrolledStudents().size());
        } else {
            System.out.println("You do not own this course.");
        }
    }

    // Adds a material object to the specified course
    public void addMaterialToCourse(Course course, CourseMaterial material) {
         if (createdCourses.contains(course)) {
            course.addMaterial(material);
            System.out.println("Added material '" + material.getTitle() + "' to " + course.getTitle());
         }
    }
}