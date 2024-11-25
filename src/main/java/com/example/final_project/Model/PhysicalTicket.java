package com.example.final_project.Model;

import java.time.LocalDateTime;

public class PhysicalTicket extends Ticket {

    public PhysicalTicket(String ticketId, LocalDateTime purchaseDateTime, Showtime showtime, String clientId) {
        super(ticketId, purchaseDateTime, showtime, clientId);
    }

    @Override
    public String getTicketType() {
        return "Physical";
    }
}