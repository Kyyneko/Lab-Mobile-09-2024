package com.example.pmd_8;

public class Note {
    private int id;
    private String title;
    private String description;
    private String createdAt;
    private String lastUpdated;

    public Note(int id, String title, String description, String createdAt, String lastUpdated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    public Note() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
