# Online Learning Management System (LMS)

A comprehensive Java-based Learning Management System with full error handling, validation, and servlet support.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [System Requirements](#system-requirements)
- [Installation & Setup](#installation--setup)
- [Compilation & Execution](#compilation--execution)
- [Project Documentation](#project-documentation)
- [Code Quality Features](#code-quality-features)
- [API Reference](#api-reference)
- [Error Handling](#error-handling)

## Overview

The Online Learning Management System is a fully-featured educational platform that enables administrators, instructors, and students to manage courses, track progress, and facilitate learning. Built with enterprise-grade Java practices including comprehensive error handling, input validation, and secure password management.

**Version:** 1.0 | **Language:** Java | **Status:** Production Ready

## Features

### Core Functionality

✅ **User Management**
- Register users with role-based access (Admin, Instructor, Student)
- Secure authentication with password hashing
- User profile management and account deactivation
- Input validation for all user data

✅ **Course Management**
- Create and manage courses with detailed syllabus
- Course approval workflow with admin oversight
- Course material management (PDF, Video, Quiz, Assignment, Lecture)
- Enrollment capacity management

✅ **Student Operations**
- Enroll in approved courses
- Access course materials
- Track progress and achievements
- Update profile information
- Drop courses

✅ **Instructor Operations**
- Create courses with detailed content
- Add and manage course materials
- Track student progress
- View enrollment statistics
- Communicate with enrolled students

✅ **Administrative Functions**
- User account management (create, update, delete, suspend)
- Course approval and rejection workflow
- System settings configuration
- Performance analytics monitoring
- User account activation/deactivation

### Advanced Features

✅ **Robust Error Handling**
- 4 custom exception classes for specific error scenarios
- Try-catch blocks throughout application
- Meaningful error messages for users
- Graceful error recovery

✅ **Comprehensive Input Validation**
- Email format validation (RFC 5322 simplified)
- Password strength (min 8 chars, uppercase, lowercase, digit, special)
- Username format (3-20 alphanumeric with underscores/hyphens)
- Course title/description length validation
- Message and syllabus validation

✅ **Servlet Implementation**
- UserServlet for user operations
- CourseServlet for course management
- EnrollmentServlet for student enrollment
- Request handling and response generation

✅ **Data Security**
- Password hashing implementation
- Input sanitization to prevent injection attacks
- Access control enforcement
- Validation utility class for centralized validation

## Project Structure

```
online-learning-management-system/
├── src/
│   └── main/
│       └── java/
│           └── lms/
│               ├── User.java                      # Base user class
│               ├── Student.java                   # Student implementation
│               ├── Instructor.java                # Instructor implementation
│               ├── Admin.java                     # Admin implementation
│               ├── Course.java                    # Course model
│               ├── CourseMaterial.java            # Course material model
│               ├── LmsManager.java                # Main application/simulation
│               ├── StudentDashboard.java          # Student interface
│               ├── InstructorDashboard.java       # Instructor interface
│               ├── AdminDashboard.java            # Admin interface
│               ├── ValidationUtil.java            # Validation utility (100+ lines)
│               ├── ValidationException.java       # Custom exception
│               ├── CourseException.java           # Custom exception
│               ├── EnrollmentException.java       # Custom exception
│               ├── UserManagementException.java   # Custom exception
│               ├── UserServlet.java               # User request handler (Servlet)
│               ├── CourseServlet.java             # Course request handler (Servlet)
│               └── EnrollmentServlet.java         # Enrollment request handler (Servlet)
├── bin/                                           # Compiled .class files
└── README.md                                      # This documentation
```

## System Requirements

- **Java Development Kit (JDK):** 8 or higher
  - Tested with JDK 11, 17, 21
  - Download from: https://jdk.java.net/
  
- **Operating System:** Windows, macOS, Linux
- **Terminal/Command Line:** PowerShell (Windows), bash (Linux/macOS)
- **Memory:** 512 MB minimum
- **Disk Space:** 100 MB

## Installation & Setup

### Step 1: Extract/Clone Project

```bash
# Extract the project to your desired location
cd "c:\Users\sachi\OneDrive\Desktop\S\online learning management system"
```

### Step 2: Verify Java Installation

```powershell
java -version
javac -version
```

## Compilation & Execution

### Method 1: PowerShell (Windows - RECOMMENDED)

```powershell
cd "c:\Users\sachi\OneDrive\Desktop\S\online learning management system"

# Compile all Java files
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d bin $files

# Run the simulation
java -cp bin lms.LmsManager
```

### Method 2: Bash (Linux/macOS)

```bash
cd /path/to/lms

mkdir -p bin

# Compile all Java files
javac -d bin src/main/java/lms/*.java

# Run the simulation
java -cp bin lms.LmsManager
```

## Project Documentation

### Class Descriptions

**User.java** (Base Class)
- Handles authentication and user profile management
- Password hashing and validation
- Account activation/deactivation
- Methods: login(), logout(), setEmail(), setPassword(), activate(), deactivate()

**Student.java**
- Enrollment management with validation
- Course access and material viewing
- Progress tracking and achievements
- Methods: enrollInCourse(), dropCourse(), accessMaterials(), trackProgress(), updateProgress()

**Instructor.java**
- Course creation with detailed content
- Course material management
- Student progress tracking
- Enrollment statistics
- Methods: createCourse(), addMaterialToCourse(), trackStudentProgress(), viewEnrollmentStats(), communicateWithStudents()

**Admin.java**
- User account management (CRUD operations)
- Course approval workflow
- System settings configuration
- Performance monitoring
- Methods: manageUser(), approveCourse(), rejectCourse(), configureSystemSettings(), monitorPerformanceAnalytics()

**Course.java**
- Course model with validation
- Student enrollment management
- Course material collection
- Approval status management
- Capacity management

**CourseMaterial.java**
- Represents course content (PDF, Video, Quiz, Assignment, Lecture)
- Material validation and type checking
- Upload tracking

**ValidationUtil.java** (Utility Class - 100+ lines)
- Centralized validation for all user inputs
- Email, password, username, course data validation
- Input sanitization for security
- Static methods for reusable validation logic

### Custom Exception Classes

1. **ValidationException** - Input validation errors
2. **CourseException** - Course operation errors
3. **EnrollmentException** - Enrollment operation errors
4. **UserManagementException** - User management errors

### Servlet Classes

**UserServlet.java**
- handleUserRegistration(username, email, password, role)
- handleUserLogin(user, password)
- handleProfileUpdate(user, newEmail, newPassword)
- handleUserLogout(user)

**CourseServlet.java**
- handleCourseCreation(instructor, title, description, syllabus)
- handleCourseApproval(admin, course)
- handleCourseRejection(admin, course)
- handleAddMaterial(instructor, course, title, type, content)
- handleEnrollmentStats(instructor, course)

**EnrollmentServlet.java**
- handleEnrollment(student, course)
- handleCourseDrop(student, course)
- handleAccessMaterials(student, course)
- handleProgressTracking(student)
- handleProgressUpdate(student, percentage)
- handleAchievementUnlock(student, achievement)
- handleStudentCommunication(instructor, course, message)

## Code Quality Features

### 1. Error Handling & Exceptions
✅ Custom exception classes for specific error types
✅ Try-catch blocks in all risky operations
✅ User-friendly error messages
✅ Graceful failure handling

### 2. Input Validation
✅ Email: RFC 5322 simplified pattern
✅ Password: 8+ chars with uppercase, lowercase, digit, special char
✅ Username: 3-20 alphanumeric
✅ Titles: 3-100 characters
✅ Descriptions: 10-1000 characters
✅ Syllabus: 10-2000 characters
✅ Messages: 1-500 characters

### 3. Code Organization
✅ Interfaces for role-based functionality
✅ Inheritance hierarchy (User → Student/Instructor/Admin)
✅ Encapsulation with private fields
✅ Single responsibility principle

### 4. Documentation
✅ JavaDoc comments on all public methods
✅ Inline comments for complex logic
✅ Class-level documentation
✅ Comprehensive README

### 5. Security
✅ Password hashing (production-ready structure)
✅ Input sanitization to prevent XSS/injection
✅ Role-based access control
✅ User status management

## API Reference

### User Registration with Validation

```java
try {
    Student student = new Student("john_doe", "john@university.edu", "SecurePass@2024!");
    System.out.println("✓ Student registered successfully");
} catch (ValidationException e) {
    System.out.println("✗ Registration failed: " + e.getMessage());
}
```

### User Authentication

```java
if (student.login("SecurePass@2024!")) {
    System.out.println("✓ Login successful");
} else {
    System.out.println("✗ Invalid credentials");
}
student.logout();
```

### Course Creation and Management

```java
try {
    Course javaCourse = instructor.createCourse(
        "Advanced Java Programming",
        "Master Java concepts and design patterns",
        "Week 1: Patterns | Week 2: Streams | Week 3: Concurrency"
    );
    
    instructor.addMaterialToCourse(javaCourse,
        new CourseMaterial("Chapter 1", "PDF", "/materials/chapter1.pdf"));
    
    admin.approveCourse(javaCourse);
} catch (CourseException | ValidationException e) {
    System.out.println("✗ Error: " + e.getMessage());
}
```

### Student Operations

```java
try {
    student.enrollInCourse(javaCourse);
    student.accessMaterials(javaCourse);
    student.updateProgress(75.5);
    student.addAchievement("Completed Module 1 with 95%");
    student.trackProgress();
} catch (EnrollmentException | CourseException | ValidationException e) {
    System.out.println("✗ Error: " + e.getMessage());
}
```

## Error Handling Examples

### Validation Errors
```java
try {
    new Student("ab", "invalid-email", "weak");
} catch (ValidationException e) {
    // "Invalid username. Must be 3-20 alphanumeric characters."
}
```

### Enrollment Errors
```java
try {
    student.enrollInCourse(unapprovedCourse);
} catch (EnrollmentException e) {
    // "Cannot enroll in 'Course Name'. Course is not yet approved."
}
```

### Course Errors
```java
try {
    instructor.createCourse("X", "desc", "syllabus");
} catch (CourseException e) {
    // "Invalid course title. Must be 3-100 characters."
}
```

## Running the Complete Simulation

Execute the main program to see all features in action:

```powershell
java -cp bin lms.LmsManager
```

**Demonstrates:**
1. User registration with validation
2. User authentication
3. Course creation and materials
4. Course approval workflow
5. Student enrollment
6. Material access
7. Profile management
8. Progress tracking
9. Instructor operations
10. Admin monitoring
11. Error handling scenarios
