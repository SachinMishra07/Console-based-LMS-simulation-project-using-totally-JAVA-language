package lms;


public class EnrollmentServlet {
    

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
