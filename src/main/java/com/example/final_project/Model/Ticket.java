package com.example.final_project.Model;

import java.time.LocalDateTime;

public abstract class Ticket {
    private String ticketId;
    private LocalDateTime purchaseDateTime;
    private Showtime showtime;
    private String clientId;

    // Constructor with input validation
    public Ticket(String ticketId, LocalDateTime purchaseDateTime, Showtime showtime, String clientId) {
        if (ticketId == null || ticketId.isEmpty()) {
            throw new IllegalArgumentException("Ticket ID cannot be null or empty");
        }
        if (purchaseDateTime == null) {
            throw new IllegalArgumentException("Purchase date/time cannot be null");
        }
        if (showtime == null) {
            throw new IllegalArgumentException("Showtime cannot be null");
        }
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("Client ID cannot be null or empty");
        }

        this.ticketId = ticketId;
        this.purchaseDateTime = purchaseDateTime;
        this.showtime = showtime;
        this.clientId = clientId;
    }

    // Getters and Setters
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        if (ticketId == null || ticketId.isEmpty()) {
            throw new IllegalArgumentException("Ticket ID cannot be null or empty");
        }
        this.ticketId = ticketId;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        if (purchaseDateTime == null) {
            throw new IllegalArgumentException("Purchase date/time cannot be null");
        }
        this.purchaseDateTime = purchaseDateTime;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        if (showtime == null) {
            throw new IllegalArgumentException("Showtime cannot be null");
        }
        this.showtime = showtime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("Client ID cannot be null or empty");
        }
        this.clientId = clientId;
    }

    // Abstract method to be implemented by subclasses
    public abstract String getTicketType();
}