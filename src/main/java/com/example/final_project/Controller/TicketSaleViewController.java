package com.example.final_project.Controller;

import com.example.final_project.Model.Ticket;
import com.example.final_project.helpers.ImportHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class TicketSaleViewController {

    @FXML
    private ListView<String> ticketSaleListView; // Displaying ticket details as strings

    @FXML
    private Button ticketSaleBackButton;

    @FXML
    private Button ticketSaleSelectButton;

    private ObservableList<String> ticketList;

    @FXML
    public void initialize() {
        // Load tickets from CSV
        List<Ticket> tickets = ImportHelper.loadTicketsFromCSV();

        // Convert tickets to displayable strings
        ticketList = FXCollections.observableArrayList();
        for (Ticket ticket : tickets) {
            ticketList.add(formatTicketForDisplay(ticket));
        }

        // Bind the ObservableList to the ListView
        ticketSaleListView.setItems(ticketList);

        // Configure the Back button to close the current view
        ticketSaleBackButton.setOnAction(event -> handleBackButton());
    }

    /**
     * Formats a ticket object into a display-friendly string.
     *
     * @param ticket the ticket to format
     * @return the formatted string
     */
    private String formatTicketForDisplay(Ticket ticket) {
        return "Ticket ID: " + ticket.getTicketId() +
                " | Type: " + ticket.getTicketType() +
                " | Movie: " + ticket.getMovieName() +
                " | Showtime: " + ticket.getScreenTime() +
                " | Room: " + ticket.getRoomId();
    }

    /**
     * Handles the "Back" button click event to close the current stage.
     */
    private void handleBackButton() {
        Stage stage = (Stage) ticketSaleBackButton.getScene().getWindow();
        stage.close();
    }
}
