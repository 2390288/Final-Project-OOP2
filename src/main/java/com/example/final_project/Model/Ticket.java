package com.example.final_project.Model;

import java.time.LocalDateTime;

/**
 * Represents a general ticket for a movie showtime.
 * This abstract class provides the common attributes and behaviors of all ticket types.
 */
public abstract class Ticket {
    private String ticketId;
    private LocalDateTime purchaseDateTime;
    private Showtime showtime;
    private String clientId;

    /**
     * Constructs a new Ticket with the specified details.
     *
     * @param ticketId        the unique identifier of the ticket. Cannot be null or empty.
     * @param purchaseDateTime the date and time the ticket was purchased. Cannot be null.
     * @param showtime         the showtime associated with the ticket. Cannot be null.
     * @param clientId         the unique identifier of the client who purchased the ticket. Cannot be null or empty.
     * @throws IllegalArgumentException if any of the input parameters are invalid.
     */
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

    /**
     * Returns the unique ticket ID.
     *
     * @return the ticket ID.
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * Sets the unique ticket ID.
     *
     * @param ticketId the new ticket ID. Cannot be null or empty.
     * @throws IllegalArgumentException if the ticket ID is null or empty.
     */
    public void setTicketId(String ticketId) {
        if (ticketId == null || ticketId.isEmpty()) {
            throw new IllegalArgumentException("Ticket ID cannot be null or empty");
        }
        this.ticketId = ticketId;
    }

    /**
     * Returns the purchase date and time of the ticket.
     *
     * @return the purchase date and time.
     */
    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    /**
     * Sets the purchase date and time of the ticket.
     *
     * @param purchaseDateTime the new purchase date and time. Cannot be null.
     * @throws IllegalArgumentException if the purchase date/time is null.
     */
    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        if (purchaseDateTime == null) {
            throw new IllegalArgumentException("Purchase date/time cannot be null");
        }
        this.purchaseDateTime = purchaseDateTime;
    }

    /**
     * Returns the showtime associated with the ticket.
     *
     * @return the showtime.
     */
    public Showtime getShowtime() {
        return showtime;
    }

    /**
     * Sets the showtime associated with the ticket.
     *
     * @param showtime the new showtime. Cannot be null.
     * @throws IllegalArgumentException if the showtime is null.
     */
    public void setShowtime(Showtime showtime) {
        if (showtime == null) {
            throw new IllegalArgumentException("Showtime cannot be null");
        }
        this.showtime = showtime;
    }

    /**
     * Returns the client ID of the ticket purchaser.
     *
     * @return the client ID.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the client ID of the ticket purchaser.
     *
     * @param clientId the new client ID. Cannot be null or empty.
     * @throws IllegalArgumentException if the client ID is null or empty.
     */
    public void setClientId(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("Client ID cannot be null or empty");
        }
        this.clientId = clientId;
    }

    /**
     * Abstract method to get the ticket type.
     * This method must be implemented by subclasses.
     *
     * @return the type of the ticket as a string.
     */
    public abstract String getTicketType();
}
