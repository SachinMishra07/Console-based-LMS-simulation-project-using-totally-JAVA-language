package lms;

/**
 * LmsManager - Main application class demonstrating the complete LMS workflow.
 * 
 * This class serves as the entry point for the Learning Management System,
 * demonstrating all major features including:
 * - User registration and authentication
 * - Course creation and approval
 * - Student enrollment and course access
 * - Progress tracking and communication
 * - Administrative operations
 * - Comprehensive error handling
 */
public class LmsManager {

    /**
     * Main method that demonstrates the complete LMS workflow.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  Online Learning Management System (LMS) - v1.0        ║");
        System.out.println("║  Comprehensive Workflow Simulation with Error Handling  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        try {
            // ===== USER REGISTRATION & AUTHENTICATION =====
            System.out.println("─── STEP 1: USER REGISTRATION ───\n");
            Admin admin = null;
            Instructor instructor = null;
            Student student = null;

            try {
                admin = new Admin("admin_user", "admin@lms.com", "Admin@123!");
                System.out.println("✓ Admin user registered successfully.");
            } catch (ValidationException e) {
                System.out.println("✗ Admin registration failed: " + e.getMessage());
                return;
            }

            try {
                instructor = new Instructor("prof_davis", "davis@university.edu", "Prof@2024!");
                System.out.println("✓ Instructor user registered successfully.");
            } catch (ValidationException e) {
                System.out.println("✗ Instructor registration failed: " + e.getMessage());
                return;
            }

            try {
                student = new Student("student_jane", "jane.student@mail.com", "Student@123!");
                System.out.println("✓ Student user registered successfully.\n");
            } catch (ValidationException e) {
                System.out.println("✗ Student registration failed: " + e.getMessage());
                return;
            }

            // ===== USER AUTHENTICATION =====
            System.out.println("─── STEP 2: USER AUTHENTICATION ───\n");
            
            if (!admin.login("Admin@123!")) {
                System.out.println("✗ Admin login failed.");
                return;
            }

            if (!instructor.login("Prof@2024!")) {
                System.out.println("✗ Instructor login failed.");
                return;
            }

            if (!student.login("Student@123!")) {
                System.out.println("✗ Student login failed.");
                return;
            }
            System.out.println();

            // ===== COURSE CREATION =====
            System.out.println("─── STEP 3: COURSE CREATION ───\n");
            Course javaCourse = null;
            Course pythonCourse = null;

            try {
                javaCourse = instructor.createCourse(
                    "Java Programming Fundamentals",
                    "Learn core Java concepts including OOP, collections, and exception handling",
                    "Week 1: Basics & Setup | Week 2: OOP Concepts | Week 3: Collections | Week 4: Advanced Topics"
                );
                System.out.println();
            } catch (CourseException e) {
                System.out.println("✗ Java course creation failed: " + e.getMessage());
            }

            try {
                pythonCourse = instructor.createCourse(
                    "Python for Data Science",
                    "Master Python programming with focus on data analysis and visualization",
                    "Week 1: Python Basics | Week 2: NumPy & Pandas | Week 3: Data Visualization | Week 4: Projects"
                );
                System.out.println();
            } catch (CourseException e) {
                System.out.println("✗ Python course creation failed: " + e.getMessage());
            }

            // ===== ADD COURSE MATERIALS =====
            System.out.println("─── STEP 4: ADD COURSE MATERIALS ───\n");
            
            if (javaCourse != null) {
                try {
                    instructor.addMaterialToCourse(javaCourse, 
                        new CourseMaterial("Chapter 1: Introduction to Java", "PDF", "/course/java/chapter1.pdf"));
                    instructor.addMaterialToCourse(javaCourse,
                        new CourseMaterial("Setup Tutorial", "Video", "/course/java/setup-tutorial.mp4"));
                    instructor.addMaterialToCourse(javaCourse,
                        new CourseMaterial("OOP Concepts Quiz", "Quiz", "/course/java/oop-quiz.html"));
                } catch (CourseException | ValidationException e) {
                    System.out.println("✗ Material addition failed: " + e.getMessage());
                }
            }
            System.out.println();

            // ===== COURSE APPROVAL =====
            System.out.println("─── STEP 5: ADMIN COURSE APPROVAL ───\n");
            
            if (javaCourse != null) {
                try {
                    admin.approveCourse(javaCourse);
                } catch (CourseException e) {
                    System.out.println("✗ Course approval failed: " + e.getMessage());
                }
            }

            if (pythonCourse != null) {
                try {
                    admin.approveCourse(pythonCourse);
                } catch (CourseException e) {
                    System.out.println("✗ Course approval failed: " + e.getMessage());
                }
            }
            System.out.println();

            // ===== STUDENT ENROLLMENT =====
            System.out.println("─── STEP 6: STUDENT ENROLLMENT ───\n");
            
            if (javaCourse != null) {
                try {
                    student.enrollInCourse(javaCourse);
                } catch (EnrollmentException e) {
                    System.out.println("✗ Enrollment failed: " + e.getMessage());
                }
            }

            if (pythonCourse != null) {
                try {
                    student.enrollInCourse(pythonCourse);
                } catch (EnrollmentException e) {
                    System.out.println("✗ Enrollment failed: " + e.getMessage());
                }
            }
            System.out.println();

            // ===== ACCESS COURSE MATERIALS =====
            System.out.println("─── STEP 7: STUDENT ACCESSES COURSE MATERIALS ───\n");
            
            if (javaCourse != null) {
                try {
                    student.accessMaterials(javaCourse);
                } catch (CourseException e) {
                    System.out.println("✗ Material access failed: " + e.getMessage());
                }
            }
            System.out.println();

            // ===== PROFILE UPDATE =====
            System.out.println("─── STEP 8: PROFILE UPDATE ───\n");
            
            try {
                student.updateProfile("jane.doe@university.edu", "NewPass@2024!");
                System.out.println();
            } catch (ValidationException e) {
                System.out.println("✗ Profile update failed: " + e.getMessage());
            }

            // ===== PROGRESS TRACKING =====
            System.out.println("─── STEP 9: PROGRESS TRACKING ───\n");
            
            try {
                student.updateProgress(45.0);
            } catch (ValidationException e) {
                System.out.println("✗ Progress update failed: " + e.getMessage());
            }

            student.addAchievement("Completed Chapter 1 Quiz with 95%");
            student.addAchievement("Submitted Programming Assignment 1");
            student.trackProgress();
            System.out.println();

            // ===== INSTRUCTOR POST-ENROLLMENT WORKFLOW =====
            System.out.println("─── STEP 10: INSTRUCTOR OPERATIONS ───\n");
            
            if (javaCourse != null) {
                try {
                    instructor.viewEnrollmentStats(javaCourse);
                    System.out.println();
                    instructor.trackStudentProgress(javaCourse);
                    System.out.println();
                    instructor.communicateWithStudents(javaCourse, 
                        "Welcome to Java Programming! Please review the syllabus and start with Chapter 1.");
                    System.out.println();
                } catch (CourseException e) {
                    System.out.println("✗ Operation failed: " + e.getMessage());
                }
            }

            // ===== ADMIN MONITORING =====
            System.out.println("─── STEP 11: ADMIN SYSTEM MONITORING ───\n");
            admin.monitorPerformanceAnalytics();
            System.out.println();

            // ===== ERROR HANDLING EXAMPLES =====
            System.out.println("─── STEP 12: ERROR HANDLING DEMONSTRATIONS ───\n");
            
            // Try to enroll with invalid course
            System.out.println("Test 1: Attempt to enroll in null course");
            try {
                student.enrollInCourse(null);
            } catch (EnrollmentException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Try to create course with invalid title
            System.out.println("Test 2: Attempt to create course with invalid title");
            try {
                instructor.createCourse("AB", "Invalid", "Course with too short title");
            } catch (CourseException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Try to register with weak password
            System.out.println("Test 3: Attempt to register with weak password");
            try {
                new Student("weak_user", "weak@test.com", "weak");
            } catch (ValidationException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Try to register with invalid email
            System.out.println("Test 4: Attempt to register with invalid email");
            try {
                new Student("valid_user", "invalid-email", "SecurePass@2024!");
            } catch (ValidationException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // ===== SIMULATION COMPLETE =====
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║              LMS Workflow Simulation Complete           ║");
            System.out.println("║        All features demonstrated successfully!         ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");

        } catch (Exception e) {
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║                   UNEXPECTED ERROR                      ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
