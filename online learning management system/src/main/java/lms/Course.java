package lms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
            throw new CourseException("Course must have a valid instructor, Can't be null");
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

    public void addStudent(Student student) throws EnrollmentException {
        if (student == null) {
            throw new EnrollmentException("Invalid student. Can't be null.");
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

    public boolean removeStudent(Student student) {
        if (enrolledStudents.remove(student)) {
            enrollmentCount--;
            System.out.println("✓ Student '" + student.getUsername() + "' removed from course '" + title + "'.");
            return true;
        }
        return false;
    }

    public void addMaterial(CourseMaterial material) throws CourseException {
        if (material == null) {
            throw new CourseException("Material can't be null.");
        }
        if (materials.contains(material)) {
            throw new CourseException("This material is already added to the course.");
        }
        materials.add(material);
        System.out.println("✓ Material '" + material.getTitle() + "' added to course '" + title + "'.");
    }

    public boolean removeMaterial(CourseMaterial material) {
        if (materials.remove(material)) {
            System.out.println("✓ Material '" + material.getTitle() + "' removed from course '" + title + "'.");
            return true;
        }
        return false;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws CourseException {
        if (!ValidationUtil.isValidCourseTitle(title)) {
            throw new CourseException("Invalid course title. Must be 3-100 characters.");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws CourseException {
        if (!ValidationUtil.isValidDescription(description)) {
            throw new CourseException("Invalid description. Must be 10-1000 characters.");
        }
        this.description = description;
    }

    public String getSyllabus() {
        return syllabus;
    }

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
