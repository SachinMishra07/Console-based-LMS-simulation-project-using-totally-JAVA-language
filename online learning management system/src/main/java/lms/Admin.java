package lms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Admin extends User implements AdminDashboard {

    private Map<String, User> userRegistry;
    private List<Course> approvalQueue;

  
    public Admin(String username, String email, String password) throws ValidationException {
        super(username, email, password);
        this.userRegistry = new HashMap<>();
        this.approvalQueue = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Admin";
    }


    @Override
    public void manageUser(User user, String action) throws UserManagementException {
        if (user == null) {
            throw new UserManagementException("Invalid user. Cannot be null.");
        }
        if (!ValidationUtil.isValidUserAction(action)) {
            throw new UserManagementException("Invalid action. Must be: create, update, delete, or suspend.");
        }

        String actionLower = action.toLowerCase();
        
        try {
            switch (actionLower) {
                case "create":
                    userRegistry.put(user.getUserId(), user);
                    System.out.println("✓ Admin " + getUsername() + " created user: " + user.getUsername());
                    break;
                case "update":
                    if (!userRegistry.containsKey(user.getUserId())) {
                        throw new UserManagementException("User not found in registry.");
                    }
                    userRegistry.put(user.getUserId(), user);
                    System.out.println("✓ Admin " + getUsername() + " updated user: " + user.getUsername());
                    break;
                case "delete":
                    if (userRegistry.remove(user.getUserId()) == null) {
                        throw new UserManagementException("User not found in registry.");
                    }
                    System.out.println("✓ Admin " + getUsername() + " deleted user: " + user.getUsername());
                    break;
                case "suspend":
                    user.deactivate();
                    System.out.println("✓ Admin " + getUsername() + " suspended user: " + user.getUsername());
                    break;
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new UserManagementException("User management action failed: " + e.getMessage(), e);
        }
    }


    @Override
    public void approveCourse(Course course) throws CourseException {
        if (course == null) {
            throw new CourseException("Invalid course. Cannot be null.");
        }
        if (course.isApproved()) {
            throw new CourseException("Course '" + course.getTitle() + "' is already approved.");
        }

        course.setApproved(true);
        approvalQueue.remove(course);
        System.out.println(" Admin " + getUsername() + " approved course: '" + course.getTitle() + "'");
        System.out.println("  Course is now live and available for enrollment.");
    }


    @Override
    public void rejectCourse(Course course) throws CourseException {
        if (course == null) {
            throw new CourseException("Invalid course. Cannot be null.");
        }

        course.setApproved(false);
        approvalQueue.remove(course);
        System.out.println(" Admin " + getUsername() + " rejected course: '" + course.getTitle() + "'");
        System.out.println("  Please contact the instructor for more information.");
    }


    @Override
    public void configureSystemSettings(String setting, String value) 
            throws UserManagementException {
        if (setting == null || setting.trim().isEmpty()) {
            throw new UserManagementException("Setting name cannot be empty.");
        }
        if (value == null || value.trim().isEmpty()) {
            throw new UserManagementException("Setting value cannot be empty.");
        }

        System.out.println("✓ Admin " + getUsername() + " configured system setting:");
        System.out.println("  Setting: " + setting);
        System.out.println("  Value: " + value);
    }


    @Override
    public void monitorPerformanceAnalytics() {
        System.out.println("\n--- System Performance Analytics ---");
        System.out.println("Total Registered Users: " + userRegistry.size());
        System.out.println("Courses Pending Approval: " + approvalQueue.size());
        System.out.println("System Status: OK");
        System.out.println("Last Updated: " + java.time.LocalDateTime.now());
        System.out.println("Platform Health: Excellent");
    }

    public void submitCourseForApproval(Course course) {
        if (course != null && !approvalQueue.contains(course)) {
            approvalQueue.add(course);
            System.out.println("Course '" + course.getTitle() + "' submitted for approval.");
        }
    }


    public List<Course> getApprovalQueue() {
        return new ArrayList<>(approvalQueue);
    }


    public User getUserById(String userId) {
        return userRegistry.get(userId);
    }


    public int getUserCount() {
        return userRegistry.size();
    }


    public void reactivateUser(User user) {
        if (user != null) {
            user.activate();
            System.out.println("Admin " + getUsername() + " reactivated user: " + user.getUsername());
        }
    }
}
