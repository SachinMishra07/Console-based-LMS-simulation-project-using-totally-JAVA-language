package lms;

/**
 * Interface defining the functionalities available to an Instructor.
 * Represents the Instructor Dashboard and instructor-specific operations.
 * 
 * Operations:
 * - Create courses with details and syllabus
 * - Track student progress and performance
 * - Communicate with enrolled students
 * - View enrollment statistics
 */
public interface InstructorDashboard {
    
    /**
     * Creates a new course with the given details.
     * @param title the course title
     * @param description the course description
     * @param syllabus the course syllabus and content outline
     * @return the created course
     * @throws CourseException if course creation fails
     */
    Course createCourse(String title, String description, String syllabus) throws CourseException;
    
    /**
     * Tracks and displays student progress in a course.
     * @param course the course to track progress for
     * @throws CourseException if operation fails
     */
    void trackStudentProgress(Course course) throws CourseException;
    
    /**
     * Sends a message to all students in a course.
     * @param course the course
     * @param message the message content
     * @throws CourseException if operation fails
     */
    void communicateWithStudents(Course course, String message) throws CourseException;
    
    /**
     * Displays enrollment statistics for a course.
     * @param course the course
     * @throws CourseException if operation fails
     */
    void viewEnrollmentStats(Course course) throws CourseException;
}
