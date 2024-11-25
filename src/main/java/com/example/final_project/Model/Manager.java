package com.example.final_project.Model;

public class Manager {
    private int managerId; // Primary Key
    private int userId; // Foreign Key

    // Getters and Setters
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

