package lms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a Course in the Learning Management System.
 * 
 * Features:
 * - Course creation and management
 * - Student enrollment tracking
 * - Course material management
 * - Course approval workflow
 * - Input validation for all course data
 */
public class Course {
    private String courseId;
    private String title;
    private String description;
    private String syllabus;
    private Instructor instructor;
    private boolean isApproved;
    private List<Student> enrolledStudents;
    private List<CourseMaterial> materials;
    private int maxCapacity;
    private int enrollmentCount;

    /**
     * Constructor for Course with validation.
     * @param title the course title
     * @param description the course description
     * @param syllabus the course syllabus
     * @param instructor the instructor creating the course
     * @throws CourseException if any parameter is invalid
     */
    public Course(String title, String description, String syllabus, Instructor instructor) 
            throws CourseException {
        if (!ValidationUtil.isValidCourseTitle(title)) {
            throw new CourseException("Invalid course title. Must be 3-100 characters.");
        }
        if (!ValidationUtil.isValidDescription(description)) {
            throw new CourseException("Invalid description. Must be 10-1000 characters.");
        }
        if (!ValidationUtil.isValidSyllabus(syllabus)) {
            throw new CourseException("Invalid syllabus. Must be 10-2000 characters.");
        }
        if (instructor == null) {
            throw new CourseException("Course must have a valid instructor.");
        }

        this.courseId = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.syllabus = syllabus;
        this.instructor = instructor;
        this.isApproved = false;
        this.enrolledStudents = new ArrayList<>();
        this.materials = new ArrayList<>();
        this.maxCapacity = 50; // Default capacity
        this.enrollmentCount = 0;
    }

    /**
     * Adds a student to the course with validation.
     * @param student the student to enroll
     * @throws EnrollmentException if enrollment fails
     */
    public void addStudent(Student student) throws EnrollmentException {
        if (student == null) {
            throw new EnrollmentException("Invalid student. Cannot be null.");
        }
        if (enrollmentCount >= maxCapacity) {
            throw new EnrollmentException("Course is at maximum capacity (" + maxCapacity + " students).");
        }
        if (enrolledStudents.contains(student)) {
            throw new EnrollmentException("Student is already enrolled in this course.");
        }
        if (!isApproved) {
            throw new EnrollmentException("Cannot enroll: Course is not yet approved.");
        }

        enrolledStudents.add(student);
        enrollmentCount++;
        System.out.println("✓ Student '" + student.getUsername() + "' added to course '" + title + "'.");
    }

    /**
     * Removes a student from the course.
     * @param student the student to remove
     * @return true if successful, false otherwise
     */
    public boolean removeStudent(Student student) {
        if (enrolledStudents.remove(student)) {
            enrollmentCount--;
            System.out.println("✓ Student '" + student.getUsername() + "' removed from course '" + title + "'.");
            return true;
        }
        return false;
    }

    /**
     * Adds material to the course with validation.
     * @param material the material to add
     * @throws CourseException if material is invalid
     */
    public void addMaterial(CourseMaterial material) throws CourseException {
        if (material == null) {
            throw new CourseException("Material cannot be null.");
        }
        if (materials.contains(material)) {
            throw new CourseException("This material is already added to the course.");
        }
        materials.add(material);
        System.out.println("✓ Material '" + material.getTitle() + "' added to course '" + title + "'.");
    }

    /**
     * Removes material from the course.
     * @param material the material to remove
     * @return true if successful, false otherwise
     */
    public boolean removeMaterial(CourseMaterial material) {
        if (materials.remove(material)) {
            System.out.println("✓ Material '" + material.getTitle() + "' removed from course '" + title + "'.");
            return true;
        }
        return false;
    }

    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Updates course title with validation.
     * @param title the new title
     * @throws CourseException if title is invalid
     */
    public void setTitle(String title) throws CourseException {
        if (!ValidationUtil.isValidCourseTitle(title)) {
            throw new CourseException("Invalid course title. Must be 3-100 characters.");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Updates course description with validation.
     * @param description the new description
     * @throws CourseException if description is invalid
     */
    public void setDescription(String description) throws CourseException {
        if (!ValidationUtil.isValidDescription(description)) {
            throw new CourseException("Invalid description. Must be 10-1000 characters.");
        }
        this.description = description;
    }

    public String getSyllabus() {
        return syllabus;
    }

    /**
     * Updates course syllabus with validation.
     * @param syllabus the new syllabus
     * @throws CourseException if syllabus is invalid
     */
    public void setSyllabus(String syllabus) throws CourseException {
        if (!ValidationUtil.isValidSyllabus(syllabus)) {
            throw new CourseException("Invalid syllabus. Must be 10-2000 characters.");
        }
        this.syllabus = syllabus;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        this.isApproved = approved;
        if (approved) {
            System.out.println("✓ Course '" + title + "' is now approved and available for enrollment.");
        } else {
            System.out.println("✓ Course '" + title + "' approval status changed to: not approved.");
        }
    }

    public List<Student> getEnrolledStudents() {
        return new ArrayList<>(enrolledStudents);
    }

    public int getEnrollmentCount() {
        return enrollmentCount;
    }

    public int getRemainingCapacity() {
        return maxCapacity - enrollmentCount;
    }

    /**
     * Sets the maximum enrollment capacity.
     * @param capacity the new capacity
     * @throws CourseException if capacity is invalid
     */
    public void setMaxCapacity(int capacity) throws CourseException {
        if (capacity <= 0) {
            throw new CourseException("Capacity must be greater than 0.");
        }
        if (capacity < enrollmentCount) {
            throw new CourseException("Cannot reduce capacity below current enrollment (" + enrollmentCount + ").");
        }
        this.maxCapacity = capacity;
        System.out.println("✓ Course capacity updated to: " + capacity);
    }

    public List<CourseMaterial> getMaterials() {
        return new ArrayList<>(materials);
    }

    /**
     * Gets material count.
     * @return number of materials in the course
     */
    public int getMaterialCount() {
        return materials.size();
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", instructor=" + instructor.getUsername() +
                ", isApproved=" + isApproved +
                ", enrollmentCount=" + enrollmentCount +
                ", maxCapacity=" + maxCapacity +
                ", materialsCount=" + materials.size() +
                '}';
    }
}
