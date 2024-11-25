package com.example.final_project.Model;

import java.time.LocalDateTime;

/**
 * Represents a client in the system
 * This class includes validation to ensure the integrity of input data
 */
public class Client {

    // Fields
    private int clientId; // Primary Key
    private int userId;   // Foreign Key
    private String emailAddress;
    private LocalDateTime signUpDate;
    private String name;

    /**
     * Constructor to initialize a Client object.
     *
     * @param clientId   Unique identifier for the client (must be positive)
     * @param userId     Associated user ID (must be positive)
     * @param email      Email address of the client (must follow valid email format)
     * @param signUpDate Date and time the client signed up (cannot be in the future)
     * @param name       Name of the client (non-empty, max 50 characters)
     */
    public Client(int clientId, int userId, String email, LocalDateTime signUpDate, String name) {
        setClientId(clientId);
        setUserId(userId);
        setEmailAddress(email);
        setSignUpDate(signUpDate);
        setName(name);
    }

    // Getters and Setters with Validation

    /**
     * Gets the client ID

     * @return Client ID
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets the client ID
     * Ensures the ID is a positive integer
     *
     * @param clientId Client ID to set
     * @throws IllegalArgumentException If clientId is not positive
     */
    public void setClientId(int clientId) {
        if (clientId <= 0) {
            throw new IllegalArgumentException("Client ID must be a positive integer.");
        }
        this.clientId = clientId;
    }

    /**
     * Gets the associated user ID
     *
     * @return User ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the associated user ID
     * Ensures the ID is a positive integer
     *
     * @param userId User ID to set
     * @throws IllegalArgumentException If userId is not positive
     */
    public void setUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive integer.");
        }
        this.userId = userId;
    }

    /**
     * Gets the email address of the client

     * @return Email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address of the client
     * Ensures the email address follows a valid format
     *
     * @param emailAddress Email address to set
     * @throws IllegalArgumentException If the email address is invalid
     */
    public void setEmailAddress(String emailAddress) {
        if (emailAddress == null || !emailAddress.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email address format.");
        }
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the sign-up date of the client
     *
     * @return Sign-up date and time
     */
    public LocalDateTime getSignUpDate() {
        return signUpDate;
    }

    /**
     * Sets the sign-up date of the client
     * Ensures the date is not null and not in the future
     *
     * @param signUpDate Sign-up date to set
     * @throws IllegalArgumentException If signUpDate is null or in the future
     */
    public void setSignUpDate(LocalDateTime signUpDate) {
        if (signUpDate == null || signUpDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Sign-up date cannot be empty or in the future.");
        }
        this.signUpDate = signUpDate;
    }

    /**
     * Gets the name of the client
     *
     * @return Client name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client
     * Ensures the name is not incomplete, non-empty, and within 50 characters maximum
     *
     * @param name Name to set.
     * @throws IllegalArgumentException If the name is null, empty, or too long
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 50) {
            throw new IllegalArgumentException("Name cannot be null, empty, or exceed 50 characters.");
        }
        this.name = name;
    }
}
