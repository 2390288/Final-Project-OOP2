package com.example.final_project.Model;

public class User {
    private int userId; // Primary Key
    private String username; // Unique Foreign Key
    private String password;

    public User(int userId, String username, String password) {
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

