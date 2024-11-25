package com.example.final_project.Model;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId; // Primary Key
    private LocalDateTime purchaseDate;
    private int roomId; // Foreign Key
    private LocalDateTime screenTimeDateTime; // Foreign Key
    private String movieName; // Foreign Key

    public Ticket(int ticketId, LocalDateTime purchaseDate, int roomId, LocalDateTime screenTime, String movieName) {
    }

    // Getters and Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getScreenTimeDateTime() {
        return screenTimeDateTime;
    }

    public void setScreenTimeDateTime(LocalDateTime screenTimeDateTime) {
        this.screenTimeDateTime = screenTimeDateTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
