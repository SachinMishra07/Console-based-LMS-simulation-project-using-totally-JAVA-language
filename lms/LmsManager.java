package lms;

public class LmsManager {

    // Main entry point for the LMS workflow simulation
    public static void main(String[] args) {
        System.out.println("--- LMS Workflow Simulation ---");

        // Initialize users with specific roles
        Admin admin = new Admin("admin_ShashikantSir", "Shashikant_Sir@lms.com", "Shashikant_Sir123");
        Instructor instructor = new Instructor("prof_Shashikant_Sir", "Shashikant_Sir@uni.com", "Shashikant_Sir123");
        Student student = new Student("studentSachin", "Sachin_@mail.com", "Sachin_123");

        // Simulate login for all users
        admin.login();
        instructor.login();
        student.login();

        System.out.println("\n--- Instructor Workflow ---");
        // Instructor creates a new course
        Course javaCourse = instructor.createCourse(
            "Java 101",
            "Introduction to Java Programming",
            "Week 1: Basics, Week 2: OOP"
        );

        // Instructor adds materials to the course
        instructor.addMaterialToCourse(javaCourse, new CourseMaterial("Chapter 1: Variables", "PDF", "/path/to/doc.pdf"));
        instructor.addMaterialToCourse(javaCourse, new CourseMaterial("Video 1: Setup", "Video", "/path/to/video.mp4"));


        System.out.println("\n--- Admin Workflow ---");
        // Admin checks analytics and approves the pending course
        admin.monitorPerformanceAnalytics();
        admin.approveCourse(javaCourse);


        System.out.println("\n--- Student Workflow ---");
        // Student enrolls in the course
        student.enrollInCourse(javaCourse);

        // Student accesses course materials
        student.accessMaterials(javaCourse);

        // Student updates their profile information
        student.updateProfile("Sachin.doe@mail.com", "Sachin123");

        // Student records progress
        student.addAchievement("Completed Chapter 1 Quiz");
        student.trackProgress();


        System.out.println("\n--- Instructor Post-Enrollment Workflow ---");
        // Instructor monitors the course
        instructor.viewEnrollmentStats(javaCourse);
        instructor.trackStudentProgress(javaCourse);
        instructor.communicateWithStudents(javaCourse, "Welcome to Java 101! Please check the syllabus.");

        System.out.println("\n--- Simulation Complete ---");
    }

}
