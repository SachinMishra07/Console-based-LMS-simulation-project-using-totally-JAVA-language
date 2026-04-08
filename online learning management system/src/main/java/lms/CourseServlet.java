package lms;

public class CourseServlet {

    public static String handleCourseCreation(Instructor instructor, String title, 
                                             String description, String syllabus) {
        try {
            if (instructor == null) {
                return "ERROR: Invalid instructor.";
            }

            // Validate inputs
            if (!ValidationUtil.isValidCourseTitle(title)) {
                return "ERROR: Invalid course title (3-100 characters required).";
            }
            if (!ValidationUtil.isValidDescription(description)) {
                return "ERROR: Invalid description (10-1000 characters required).";
            }
            if (!ValidationUtil.isValidSyllabus(syllabus)) {
                return "ERROR: Invalid syllabus (10-2000 characters required).";
            }

            Course course = instructor.createCourse(title, description, syllabus);
            return "SUCCESS: Course created with ID: " + course.getCourseId();
        } catch (CourseException e) {
            return "ERROR: Course creation failed - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }


    public static String handleCourseApproval(Admin admin, Course course) {
        try {
            if (admin == null || course == null) {
                return "ERROR: Invalid admin or course.";
            }

            admin.approveCourse(course);
            return "SUCCESS: Course '" + course.getTitle() + "' approved.";
        } catch (CourseException e) {
            return "ERROR: Approval failed - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }


    public static String handleCourseRejection(Admin admin, Course course) {
        try {
            if (admin == null || course == null) {
                return "ERROR: Invalid admin or course.";
            }

            admin.rejectCourse(course);
            return "SUCCESS: Course '" + course.getTitle() + "' rejected.";
        } catch (CourseException e) {
            return "ERROR: Rejection failed - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }

    public static String handleAddMaterial(Instructor instructor, Course course, 
                                          String title, String type, String content) {
        try {
            if (instructor == null || course == null) {
                return "ERROR: Invalid instructor or course.";
            }

            CourseMaterial material = new CourseMaterial(title, type, content);
            instructor.addMaterialToCourse(course, material);
            return "SUCCESS: Material added to course.";
        } catch (CourseException e) {
            return "ERROR: Material addition failed - " + e.getMessage();
        } catch (ValidationException e) {
            return "ERROR: Invalid material - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }

    public static String handleEnrollmentStats(Instructor instructor, Course course) {
        try {
            if (instructor == null || course == null) {
                return "ERROR: Invalid instructor or course.";
            }

            instructor.viewEnrollmentStats(course);
            return "SUCCESS: Enrollment statistics retrieved.";
        } catch (CourseException e) {
            return "ERROR: Failed to retrieve stats - " + e.getMessage();
        } catch (Exception e) {
            return "ERROR: Unexpected error - " + e.getMessage();
        }
    }
}
