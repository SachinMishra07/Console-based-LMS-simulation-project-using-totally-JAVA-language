package lms;

import java.util.ArrayList;
import java.util.List;


public class Instructor extends User implements InstructorDashboard {

    private List<Course> createdCourses;

    public Instructor(String username, String email, String password) throws ValidationException {
        super(username, email, password);
        this.createdCourses = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

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

    public int getCourseCount() {
        return createdCourses.size();
    }

    public List<Course> getCreatedCourses() {
        return new ArrayList<>(createdCourses);
    }

    public boolean ownsCourse(Course course) {
        return createdCourses.contains(course);
    }
}
