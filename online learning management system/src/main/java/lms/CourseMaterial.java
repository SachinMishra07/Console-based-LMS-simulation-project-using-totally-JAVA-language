package lms;

import java.util.UUID;

public class CourseMaterial {
    private String materialId;
    private String title;
    private String type; // PDF, Video, Quiz, Assignment, Lecture
    private String content; // File path or URL
    private long uploadDate;

    public CourseMaterial(String title, String type, String content) throws ValidationException {
        if (title == null || title.trim().isEmpty()) {
            throw new ValidationException("Material title cannot be empty.");
        }
        if (!ValidationUtil.isValidMaterialType(type)) {
            throw new ValidationException("Invalid material type. Must be: PDF, Video, Quiz, Assignment, or Lecture.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new ValidationException("Material content/path cannot be empty.");
        }

        this.materialId = UUID.randomUUID().toString();
        this.title = title;
        this.type = type;
        this.content = content;
        this.uploadDate = System.currentTimeMillis();
    }

    // Getters
    public String getMaterialId() {
        return materialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws ValidationException {
        if (title == null || title.trim().isEmpty()) {
            throw new ValidationException("Material title cannot be empty.");
        }
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws ValidationException {
        if (!ValidationUtil.isValidMaterialType(type)) {
            throw new ValidationException("Invalid material type. Must be: PDF, Video, Quiz, Assignment, or Lecture.");
        }
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws ValidationException {
        if (content == null || content.trim().isEmpty()) {
            throw new ValidationException("Material content/path cannot be empty.");
        }
        this.content = content;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    @Override
    public String toString() {
        return "CourseMaterial{" +
                "materialId='" + materialId + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof CourseMaterial)) return false;
        CourseMaterial other = (CourseMaterial) obj;
        return materialId.equals(other.materialId);
    }

    @Override
    public int hashCode() {
        return materialId.hashCode();
    }
}
