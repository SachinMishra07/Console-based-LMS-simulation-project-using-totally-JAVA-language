Learning Management System (LMS) Simulation

A Java-based console application that simulates the core workflows of a Learning Management System. This project demonstrates Object-Oriented Programming (OOP) concepts such as inheritance, encapsulation, polymorphism, and interface implementation.

Project Structure

All source code is contained within the lms package.

Core Files

LmsManager.java: The main entry point of the application. It contains the main method that runs the simulation workflow.

Course.java: Represents a course entity containing details like title, syllabus, and enrolled students.

CourseMaterial.java: Represents learning materials (PDFs, Videos, etc.) added to courses.

User Roles (Inheritance)

User.java: Abstract base class defining common attributes (ID, username, email) and behaviors (login, logout) for all users.

Admin.java: Subclass representing administrators who manage users and approve courses.

Instructor.java: Subclass representing instructors who create courses and track student progress.

Student.java: Subclass representing students who enroll in courses and access materials.

Interfaces (Dashboards)

AdminDashboard.java: Defines administrative capabilities.

InstructorDashboard.java: Defines course management capabilities.

StudentDashboard.java: Defines learning capabilities.

How to Run

Prerequisites

Java Development Kit (JDK) 8 or higher installed.

Compilation & Execution

Folder Setup: Ensure all your .java files are inside a folder named lms.

Open Terminal: Navigate to the directory containing the lms folder (not inside the lms folder itself).

Compile:

javac lmsManager.java


Run:

java lmsManager


Simulation Workflow

When you run LmsManager, the application simulates the following lifecycle:

Initialization: Creates an Admin, Instructor, and Student.

Login: All users log in to the system.

Content Creation: The Instructor creates a "Java 101" course and adds materials (PDFs, Videos).

Administrative Approval: The Admin monitors analytics and approves the new course to make it live.

Enrollment: The Student enrolls in the approved course.

Learning: The Student accesses course materials and tracks their progress.

Management: The Instructor views enrollment stats and sends a welcome message to students.

Note on Output


The console output currently uses the placeholder "MyName" in various status messages (e.g., login/logout confirmations) as requested. You can modify the System.out.println statements in the respective classes to use dynamic variable names (like this.username) if needed.
