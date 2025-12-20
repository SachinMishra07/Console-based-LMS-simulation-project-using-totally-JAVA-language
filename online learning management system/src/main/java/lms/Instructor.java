package lms;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Instructor user.
 * Inherits from User and implements the InstructorDashboard functionalities.
 * 
 * Features:
 * - Create and manage courses with validation
 * - Track student progress
 * - Communicate with students
 * - View enrollment statistics
 * - Manage course materials
 * - Error handling for invalid operations
 */
public class Instructor extends User implements InstructorDashboard {

    private List<Course> createdCourses;

    /**
     * Constructor for Instructor with validation.
     * @param username the username
     * @param email the email address
     * @param password the password
     * @throws ValidationException if any parameter is invalid
     */
    public Instructor(String username, String email, String password) throws ValidationException {
        super(username, email, password);
        this.createdCourses = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

    /**
     * Creates a new course with validation.
     * @param title the course title
     * @param description the course description
     * @param syllabus the course syllabus
     * @return the created course
     * @throws CourseException if course creation fails
     */
    @Override
    public Course createCourse(String title, String description, String syllabus) 
            throws CourseException {
        try {
            Course newCourse = new Course(title, description, syllabus, this);
            createdCourses.add(newCourse);
            System.out.println("✓ Course '" + title + "' created by " + getUsername() + ".");
            System.out.println("  Status: Pending admin approval");
            return newCourse;
        } catch (CourseException e) {
            System.out.println("ERROR: Failed to create course - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Tracks student progress in a course.
     * @param course the course
     * @throws CourseException if course is not owned by this instructor
     */
    @Override
    public void trackStudentProgress(Course course) throws CourseException {
        if (course == null) {
            throw new CourseException("Invalid course. Cannot be null.");
        }
        if (!createdCourses.contains(course)) {
            throw new CourseException("You do not own this course.");
        }

        List<Student> students = course.getEnrolledStudents();
        System.out.println("\n--- Progress Report for '" + course.getTitle() + "' ---");
        System.out.println("Total enrolled students: " + students.size());
        
        if (students.isEmpty()) {
            System.out.println("No students enrolled yet.");
        } else {
            for (Student student : students) {
                System.out.println("  • " + student.getUsername() + 
                                 " - Progress: " + String.format("%.2f", student.getProgressPercentage()) + "%");
            }
        }
    }

    /**
     * Sends a message to all students in a course.
     * @param course the course
     * @param message the message content
     * @throws CourseException if operation fails
     */
    @Override
    public void communicateWithStudents(Course course, String message) 
            throws CourseException {
        if (course == null) {
            throw new CourseException("Invalid course. Cannot be null.");
        }
        if (!createdCourses.contains(course)) {
            throw new CourseException("You do not own this course.");
        }
        if (!ValidationUtil.isValidMessage(message)) {
            throw new CourseException("Message must be between 1 and 500 characters.");
        }

        List<Student> students = course.getEnrolledStudents();
        System.out.println("\n--- Broadcasting Message ---");
        System.out.println("Course: " + course.getTitle());
        System.out.println("From: " + getUsername());
        System.out.println("Message: " + message);
        System.out.println("Sent to " + students.size() + " student(s)");
        
        if (!students.isEmpty()) {
            System.out.println("✓ Message delivered successfully.");
        }
    }

    /**
     * Views enrollment statistics for a course.
     * @param course the course
     * @throws CourseException if operation fails
     */
    @Override
    public void viewEnrollmentStats(Course course) throws CourseException {
        if (course == null) {
            throw new CourseException("Invalid course. Cannot be null.");
        }
        if (!createdCourses.contains(course)) {
            throw new CourseException("You do not own this course.");
        }

        System.out.println("\n--- Enrollment Statistics for '" + course.getTitle() + "' ---");
        System.out.println("Enrolled Students: " + course.getEnrollmentCount() + " / " + 50);
        System.out.println("Remaining Capacity: " + course.getRemainingCapacity());
        System.out.println("Course Status: " + (course.isApproved() ? "Approved" : "Pending"));
        System.out.println("Course Materials: " + course.getMaterialCount());
        System.out.println("Enrollment Rate: " + String.format("%.2f", 
            (course.getEnrollmentCount() / 50.0) * 100) + "%");
    }

    /**
     * Adds material to a course with validation.
     * @param course the course
     * @param material the material to add
     * @throws CourseException if operation fails
     */
    public void addMaterialToCourse(Course course, CourseMaterial material) 
            throws CourseException {
        if (course == null) {
            throw new CourseException("Invalid course. Cannot be null.");
        }
        if (material == null) {
            throw new CourseException("Invalid material. Cannot be null.");
        }
        if (!createdCourses.contains(course)) {
            throw new CourseException("You do not own this course.");
        }

        course.addMaterial(material);
    }

    /**
     * Removes material from a course.
     * @param course the course
     * @param material the material to remove
     * @return true if successful, false otherwise
     */
    public boolean removeMaterialFromCourse(Course course, CourseMaterial material) {
        if (course == null || material == null) {
            System.out.println("ERROR: Invalid course or material.");
            return false;
        }
        if (!createdCourses.contains(course)) {
            System.out.println("ERROR: You do not own this course.");
            return false;
        }
        return course.removeMaterial(material);
    }

    /**
     * Gets the count of courses created by this instructor.
     * @return number of created courses
     */
    public int getCourseCount() {
        return createdCourses.size();
    }

    /**
     * Gets list of courses created by this instructor.
     * @return list of courses
     */
    public List<Course> getCreatedCourses() {
        return new ArrayList<>(createdCourses);
    }

    /**
     * Checks if instructor owns a course.
     * @param course the course to check
     * @return true if instructor owns the course
     */
    public boolean ownsCourse(Course course) {
        return createdCourses.contains(course);
    }
}
