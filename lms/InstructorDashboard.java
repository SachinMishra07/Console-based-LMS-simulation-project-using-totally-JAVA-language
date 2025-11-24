package lms;

public interface InstructorDashboard {
    // Defines method to create a new course
    Course createCourse(String title, String description, String syllabus);
    // Defines method to track progress of students
    void trackStudentProgress(Course course);
    // Defines method to communicate with students
    void communicateWithStudents(Course course, String message);
    // Defines method to view enrollment statistics
    void viewEnrollmentStats(Course course);
}