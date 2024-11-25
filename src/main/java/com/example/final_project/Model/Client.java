package com.example.final_project.Model;

import java.time.LocalDateTime;

import java.time.LocalDateTime;
public class Client {
    private int clientId; // Primary Key
    private int userId; // Foreign Key
    private String emailAddress;
    private LocalDateTime signUpDate;
    private String name;

    public Client(int clientId, int userId, String email, LocalDateTime signUpDate, String name) {
    }

    // Getters and Setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDateTime getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDateTime signUpDate) {
        this.signUpDate = signUpDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

