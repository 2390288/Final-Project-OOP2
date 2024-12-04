package com.example.final_project.Model;

import java.time.LocalDateTime;

/**
 * Represents a movie ticket with details such as purchase date, screening time, room ID, and movie name.
 */
public class DigitalTicket {

    private int ticketId;
    private LocalDateTime purchaseDateTime;
    private int roomId;
    private LocalDateTime screenTime;
    private String movieName;

    /**
     * Constructs a new DigitalTicket with the specified details.
     *
     * @param ticketId         the unique identifier for the ticket. Must be positive.
     * @param purchaseDateTime the date and time the ticket was purchased. Cannot be null or in the future.
     * @param roomId           the ID of the screening room. Must be positive.
     * @param screenTime       the date and time of the movie screening. Cannot be null or before the purchase date.
     * @param movieName        the name of the movie. Cannot be null or empty.
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    public DigitalTicket(int ticketId, LocalDateTime purchaseDateTime, int roomId, LocalDateTime screenTime, String movieName) {
        setTicketId(ticketId);
        setPurchaseDateTime(purchaseDateTime);
        setRoomId(roomId);
        setScreenTime(screenTime, purchaseDateTime);
        setMovieName(movieName);
    }

    // Getters and Setters with validation

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        if (ticketId <= 0) {
            throw new IllegalArgumentException("Ticket ID must be a positive integer.");
        }
        this.ticketId = ticketId;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        if (purchaseDateTime == null || purchaseDateTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Purchase date must not be null or in the future.");
        }
        this.purchaseDateTime = purchaseDateTime;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        if (roomId <= 0) {
            throw new IllegalArgumentException("Room ID must be a positive integer.");
        }
        this.roomId = roomId;
    }

    public LocalDateTime getScreenTime() {
        return screenTime;
    }

    public void setScreenTime(LocalDateTime screenTime, LocalDateTime purchaseDateTime) {
        if (screenTime == null) {
            throw new IllegalArgumentException("Screen time cannot be null.");
        }
        if (purchaseDateTime != null && screenTime.isBefore(purchaseDateTime)) {
            throw new IllegalArgumentException("Screen time cannot be before the purchase date.");
        }
        this.screenTime = screenTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        if (movieName == null || movieName.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie name cannot be null or empty.");
        }
        this.movieName = movieName;
    }
}
