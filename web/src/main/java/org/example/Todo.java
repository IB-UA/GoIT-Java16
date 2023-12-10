package org.example;

public class Todo {
    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
