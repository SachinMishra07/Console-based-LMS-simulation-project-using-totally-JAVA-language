package lms;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseId;
    private String title;
    private String description;
    private String syllabus;
    private Instructor instructor;
    private List<Student> enrolledStudents;
    private List<CourseMaterial> materials;
    private boolean isApproved;

    // Constructor to initialize a new course
    public Course(String title, String description, String syllabus, Instructor instructor) {
        this.courseId = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.syllabus = syllabus;
        this.instructor = instructor;
        this.enrolledStudents = new ArrayList<>();
        this.materials = new ArrayList<>();
        this.isApproved = false;
    }

    // Adds a student to the list of enrolled students
    public void addStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    // Adds a material item to the course
    public void addMaterial(CourseMaterial material) {
        materials.add(material);
    }

    // Getter for course title
    public String getTitle() {
        return title;
    }

    // Getter for course instructor
    public Instructor getInstructor() {
        return instructor;
    }

    // Getter for enrolled students list
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Getter for course materials list
    public List<CourseMaterial> getMaterials() {
        return materials;
    }

    // Checks if the course is approved
    public boolean isApproved() {
        return isApproved;
    }

    // Sets the approval status of the course
    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}