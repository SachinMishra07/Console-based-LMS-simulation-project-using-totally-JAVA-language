package lms;

public interface InstructorDashboard {

    Course createCourse(String title, String description, String syllabus) throws CourseException;

    void trackStudentProgress(Course course) throws CourseException;

    void communicateWithStudents(Course course, String message) throws CourseException;
 
    void viewEnrollmentStats(Course course) throws CourseException;
}
