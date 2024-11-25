package com.example.final_project.Model;

import java.time.LocalDateTime;

/**
 * Represents a digital ticket for a movie showtime.
 * This subclass extends the abstract {@link Ticket} class and defines its type as "Digital".
 */
public class DigitalTicket extends Ticket {

    /**
     * Constructs a new DigitalTicket with the specified details.
     *
     * @param ticketId        the unique identifier of the ticket. Cannot be null or empty.
     * @param purchaseDateTime the date and time the ticket was purchased. Cannot be null.
     * @param showtime         the showtime associated with the ticket. Cannot be null.
     * @param clientId         the unique identifier of the client who purchased the ticket. Cannot be null or empty.
     * @throws IllegalArgumentException if any of the input parameters are invalid (see {@link Ticket}).
     */
    public DigitalTicket(String ticketId, LocalDateTime purchaseDateTime, Showtime showtime, String clientId) {
        super(ticketId, purchaseDateTime, showtime, clientId);
    }

    /**
     * Returns the type of this ticket.
     *
     * @return a string representing the type of the ticket, which is always "Digital" for this class.
     */
    @Override
    public String getTicketType() {
        return "Digital";
    }
}
