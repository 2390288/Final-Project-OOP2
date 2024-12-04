package Controller;

import com.example.final_project.Model.DigitalTicket;
import com.example.final_project.Model.PhysicalTicket;
import com.example.final_project.Model.Showtime;
import com.example.final_project.Model.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketController {
    private Ticket digitalTicket; // Stores the generated digital ticket
    private Ticket physicalTicket; // Stores the generated physical ticket
    private final List<Ticket> allTickets; // Stores all created tickets

    @FXML
    private Label eTicketTitleLabel;
    @FXML
    private Label ticketUniqueIdLabel;
    @FXML
    private Label purchasedDateLabel;
    @FXML
    private Label movieNameInfoLabel;
    @FXML
    private Label dateTimeInfoLabel;
    @FXML
    private Label screeningRoomInfoLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button ticketSaveButton;

    /**
     * Constructor to initialize ticket storage.
     */
    public TicketController() {
        this.allTickets = new ArrayList<>();
    }

    /**
     * Handles the creation of both digital and physical tickets based on the selected showtime and client ID.
     *
     * @param showtime The selected showtime for the ticket.
     * @param clientId The ID of the currently logged-in client.
     */
    public void createAndDisplayTickets(Showtime showtime, String clientId) {
        // Generate tickets
        String digitalTicketId = UUID.randomUUID().toString();
        String physicalTicketId = UUID.randomUUID().toString();
        LocalDateTime purchaseDateTime = LocalDateTime.now();

        digitalTicket = new DigitalTicket(digitalTicketId, purchaseDateTime, showtime, clientId);
        physicalTicket = new PhysicalTicket(physicalTicketId, purchaseDateTime, showtime, clientId);

        // Add tickets to the storage
        allTickets.add(digitalTicket);
        allTickets.add(physicalTicket);

        // Display digital ticket details in the view
        displayDigitalTicketDetails();
    }

    /**
     * Populates the digital ticket details in the view.
     */
    private void displayDigitalTicketDetails() {
        if (digitalTicket != null) {
            ticketUniqueIdLabel.setText(digitalTicket.getTicketId());
            purchasedDateLabel.setText(digitalTicket.getPurchaseDateTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            Showtime showtime = digitalTicket.getShowtime();
            movieNameInfoLabel.setText(showtime.getMovie().getTitle());
            dateTimeInfoLabel.setText(showtime.getDateTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            screeningRoomInfoLabel.setText(showtime.getScreeningRoom().getName());
        } else {
            clearTicketDetails();
        }
    }

    /**
     * Clears ticket details in the view.
     */
    private void clearTicketDetails() {
        ticketUniqueIdLabel.setText("N/A");
        purchasedDateLabel.setText("N/A");
        movieNameInfoLabel.setText("N/A");
        dateTimeInfoLabel.setText("N/A");
        screeningRoomInfoLabel.setText("N/A");
    }

    @FXML
    public void initialize() {
        eTicketTitleLabel.setText("E-Ticket");
    }

    @FXML
    private void handleBackButtonAction() {
        // Navigate back to the showtime selection or main menu
        System.out.println("Back button clicked. Navigate to the previous screen.");
    }

    @FXML
    private void handleSaveButtonAction() {
        if (digitalTicket != null) {
            System.out.println("Digital ticket saved with ID: " + digitalTicket.getTicketId());
            // Logic for saving the ticket (e.g., export to file or database)
        } else {
            System.out.println("No digital ticket to save.");
        }
    }

    /**
     * Gets all created digital tickets.
     *
     * @return A list of all digital tickets.
     */
    public List<DigitalTicket> getAllDigitalTickets() {
        List<DigitalTicket> digitalTickets = new ArrayList<>();
        for (Ticket ticket : allTickets) {
            if (ticket instanceof DigitalTicket) {
                digitalTickets.add((DigitalTicket) ticket); // Add only DigitalTicket objects
            }
        }
        return digitalTickets;
    }
}
