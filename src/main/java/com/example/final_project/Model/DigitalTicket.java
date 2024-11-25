package com.example.final_project.Model;

import java.time.LocalDateTime;

public class DigitalTicket extends Ticket {

    public DigitalTicket(String ticketId, LocalDateTime purchaseDateTime, Showtime showtime, String clientId) {
        super(ticketId, purchaseDateTime, showtime, clientId);
    }

    @Override
    public String getTicketType() {
        return "Digital";
    }
}