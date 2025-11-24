package lms;

public class CourseMaterial {
    private String materialId;
    private String title;
    private String type;
    private String content;

    // Constructor to initialize course material
    public CourseMaterial(String title, String type, String content) {
        this.materialId = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.type = type;
        this.content = content;
    }

    // Getter for material title
    public String getTitle() {
        return title;
    }

    // Getter for material type
    public String getType() {
        return type;
    }

    // Getter for material content
    public String getContent() {
        return content;
    }
}