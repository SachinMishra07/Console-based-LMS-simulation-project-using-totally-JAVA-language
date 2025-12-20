package lms;

/**
 * EnrollmentServlet - Handles enrollment-related HTTP requests.
 * 
 * This servlet demonstrates how LMS enrollment operations would be handled
 * in a web application context. In a real application, this would
 * integrate with servlets in a web server.
 */
public class EnrollmentServlet {
    
    /**
     * Simulates handling course enrollment request.
     * @param student the student enrolling
     * @param course the course to enroll in
     * @return response message
     */
    public static String handleEnrollment(Student student, Course course) {
        try {
            if (student == null || course == null) {
                return "ERROR: Invalid student or course.";
            }

            student.enrollInCourse(course);
            return "SUCCESS: Student enrolled in course.";
        } catch (EnrollmentException e) {
            return "ERROR: Enrollment failed - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }

    /**
     * Simulates handling course drop request.
     * @param student the student dropping the course
     * @param course the course to drop
     * @return response message
     */
    public static String handleCourseDrop(Student student, Course course) {
        try {
            if (student == null || course == null) {
                return "ERROR: Invalid student or course.";
            }

            if (student.dropCourse(course)) {
                return "SUCCESS: Course dropped successfully.";
            } else {
                return "ERROR: Failed to drop course.";
            }
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }

    /**
     * Simulates handling course material access request.
     * @param student the student accessing materials
     * @param course the course
     * @return response message
     */
    public static String handleAccessMaterials(Student student, Course course) {
        try {
            if (student == null || course == null) {
                return "ERROR: Invalid student or course.";
            }

            student.accessMaterials(course);
            return "SUCCESS: Materials retrieved.";
        } catch (CourseException e) {
            return "ERROR: Cannot access materials - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }

    /**
     * Simulates handling progress tracking request.
     * @param student the student
     * @return response message
     */
    public static String handleProgressTracking(Student student) {
        try {
            if (student == null) {
                return "ERROR: Invalid student.";
            }

            student.trackProgress();
            return "SUCCESS: Progress report generated.";
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }

    /**
     * Simulates handling progress update request.
     * @param student the student
     * @param progressPercentage the progress percentage (0-100)
     * @return response message
     */
    public static String handleProgressUpdate(Student student, double progressPercentage) {
        try {
            if (student == null) {
                return "ERROR: Invalid student.";
            }

            student.updateProgress(progressPercentage);
            return "SUCCESS: Progress updated to " + progressPercentage + "%.";
        } catch (ValidationException e) {
            return "ERROR: Invalid progress value - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }

    /**
     * Simulates handling achievement unlock request.
     * @param student the student
     * @param achievement the achievement description
     * @return response message
     */
    public static String handleAchievementUnlock(Student student, String achievement) {
        try {
            if (student == null) {
                return "ERROR: Invalid student.";
            }
            if (achievement == null || achievement.trim().isEmpty()) {
                return "ERROR: Invalid achievement.";
            }

            student.addAchievement(achievement);
            return "SUCCESS: Achievement unlocked.";
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }

    /**
     * Simulates handling student communication request.
     * @param instructor the instructor
     * @param course the course
     * @param message the message to send
     * @return response message
     */
    public static String handleStudentCommunication(Instructor instructor, Course course, String message) {
        try {
            if (instructor == null || course == null) {
                return "ERROR: Invalid instructor or course.";
            }

            instructor.communicateWithStudents(course, message);
            return "SUCCESS: Message sent to all enrolled students.";
        } catch (CourseException e) {
            return "ERROR: Communication failed - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }
}
